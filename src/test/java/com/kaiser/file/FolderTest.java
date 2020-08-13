package com.kaiser.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.kaiser.iterator.CompositeIterator;
import com.kaiser.iterator.NullIterator;

public class FolderTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@After
	public void tearDown() {
	    System.setOut(standardOut);
	}
	
	@Test
	public void should_throw_exception_from_create_folder_whne_name_is_null() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Folder.FOLDER_NAME_EXCEPTION_MESSAGE);
		
		new Folder(null);	
	}
	
	@Test
	public void should_throw_exception_from_create_folder_whne_name_is_empty() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Folder.FOLDER_NAME_EXCEPTION_MESSAGE);
		
		new Folder("");	
	}
	
	@Test
	public void should_create_folder() {
		Folder folder = new Folder("Apple");
		
		assertNotNull(folder);
		assertTrue(folder instanceof Node);
	}
	
	@Test
	public void should_throw_exception_when_null_object_passed_to_add_folder() {
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage(Folder.ADD_EXCEPTION_MESSAGE);
		
		Folder folder = new Folder("Apple");	
		folder.add(null);
	}
	
	@Test
	public void should_add_file_to_folder() {
		Folder folder = new Folder("Apple");
		File file = new File("Pie");
		
		folder.add(file);
		
		Iterator<Node> nodes = folder.createIterator();
		
		assertTrue(nodes.hasNext());
		assertSame(file, nodes.next());
	}
	
	@Test
	public void should_add_folder_to_folder() {
		Folder folder = new Folder("Apple");
		Folder subfolder = new Folder("Peach");
		
		folder.add(subfolder);
		
		Iterator<Node> nodes = folder.createIterator();
		
		assertTrue(nodes.hasNext());
		assertSame(subfolder, nodes.next());
	}
	
	@Test
	public void should_get_the_size_of_the_content_of_a_folder() {
		Folder folder = new Folder("Parent");
		
		File file1 = new File("Hello");
		file1.write("Hello world");
		
		File file2 = new File("Bye");
		file2.write("Bye, World!");
		
		folder.add(file1);
		folder.add(file2);
		
		assertEquals(file1.size() + file2.size(), folder.size());
		
	}
	
	@Test
	public void should_get_the_size_of_the_content_of_a_folder_includes_subfolders() {
		Folder folder = new Folder("Parent");
		
		File file1 = new File("Hello");
		file1.write("Hello world");
		
		Folder subfolder = new Folder("child");
		File file2 = new File("Bye");
		file2.write("Bye, World!");
		subfolder.add(file2);
		
		folder.add(file1);
		folder.add(subfolder);
		
		assertEquals(file1.size() + file2.size(), folder.size());
		
	}
	
	@Test
	public void should_print_empty_when_no_file_inside_a_folder() {
		Folder folder = new Folder("Parent");
		
		folder.list();
		
		assertEquals("", outputStreamCaptor.toString().trim());
		
	}
	
	@Test
	public void should_print_the_file_names_inside_a_folder() {
		Folder folder = new Folder("Parent");
		File file = new File("Pie");
		folder.add(file);
		
		folder.list();
		
		assertEquals(file.name, outputStreamCaptor.toString().trim());
		
	}
	
	@Test
	public void should_print_the_file_names_and_ignore_subfolders_inside_a_folder() {
		Folder folder = new Folder("Parent");
		File file = new File("Pie");
		Folder subfolder = new Folder("subfolder");
		
		folder.add(subfolder);
		folder.add(file);
		
		folder.list();
		String output = outputStreamCaptor.toString().trim();
		
		assertFalse(output.contains(subfolder.getName()));
		assertEquals(file.name, output);
		
	}
	
	@Test
	public void should_print_the_file_names_alphabetically_inside_a_folder() {
		Folder folder = new Folder("Parent");
		File file1 = new File("Pie");
		File file2 = new File("Apple");
		File file3 = new File("Zero");
		Folder subfolder = new Folder("Peach");
		
		folder.add(subfolder);
		folder.add(file1);
		folder.add(file2);
		folder.add(file3);
		
		folder.list();
		String output = outputStreamCaptor.toString().trim();
		
		String expected = new StringBuilder()
				.append(file2.getName())
				.append(System.lineSeparator())
				.append(file1.getName())
				.append(System.lineSeparator())
				.append(file3.getName())
				.toString();
		
		assertEquals(expected, output);
		
	}
	
	@Test
	public void should_walk_the_tree_of_a_folder_including_all_of_its_subfolders() {
		Folder folder = new Folder("Car");
		
		Folder subfolder1 = new Folder("Seat");
		subfolder1.add(new File("Cat"));
		subfolder1.add(new File("Ant"));
		
		Folder subfolder2 = new Folder("Engine");
		subfolder2.add(new File("Piston"));
		subfolder2.add(new File("Cylinder"));
		
		Folder subfolder3 = new Folder("Gear");
		subfolder3.add(new File("Manual"));
		subfolder3.add(new File("Auto"));
		subfolder1.add(subfolder3);
		
		folder.add(new File("Wheel"));
		folder.add(subfolder1);
		folder.add(subfolder2);
	
		folder.tree();	
		
		String output = outputStreamCaptor.toString().trim();
		
		String expected = new StringBuilder()
				.append("Wheel")
				.append(System.lineSeparator())
				.append("Seat")
				.append(System.lineSeparator())
				.append("Cat")
				.append(System.lineSeparator())
				.append("Ant")
				.append(System.lineSeparator())
				.append("Gear")
				.append(System.lineSeparator())
				.append("Manual")
				.append(System.lineSeparator())
				.append("Auto")
				.append(System.lineSeparator())
				.append("Engine")
				.append(System.lineSeparator())
				.append("Piston")
				.append(System.lineSeparator())
				.append("Cylinder")
				.toString();
		
		assertEquals(expected, output);
	}
	
	@Test
	public void should_tree_print_empty_when_no_file_inside_a_folder() {
		Folder folder = new Folder("Parent");
		
		folder.tree();
		
		assertEquals("", outputStreamCaptor.toString().trim());
		
	}
	
	@Test
	public void should_return_iterator_with_has_next_true_when_child_added() {
		Folder folder = new Folder("Parent");	
		File file = new File("Pie");
		folder.add(file);
		
		Iterator<Node> iterator = folder.createIterator();
		
		assertTrue(iterator instanceof CompositeIterator);
		assertTrue(iterator.hasNext());
	}
	
	
}
