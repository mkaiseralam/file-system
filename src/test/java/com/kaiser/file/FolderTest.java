package com.kaiser.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FolderTest {
	
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
	public void should_create_folder() {
		Folder folder = new Folder("Apple");
		
		assertNotNull(folder);
		assertTrue(folder instanceof Node);
	}
	
	@Test
	public void should_add_file_to_folder() {
		Folder folder = new Folder("Apple");
		File file = new File("Pie");
		
		folder.add(file);
		
		TreeSet<Node> nodes = folder.getNodes();
		
		assertFalse(nodes.isEmpty());
		assertSame(file, nodes.first());
	}
	
	@Test
	public void should_add_folder_to_folder() {
		Folder folder = new Folder("Apple");
		Folder subfolder = new Folder("Peach");
		
		folder.add(subfolder);
		
		TreeSet<Node> nodes = folder.getNodes();
		
		assertFalse(nodes.isEmpty());
		assertSame(subfolder, nodes.first());
	}
	
	@Test
	public void should_ignore_adding_duplicate_file_or_folder() {
		Folder folder = new Folder("Apple");
		Folder subfolder = new Folder("Peach");
		File file = new File("Pie");
		
		folder.add(file);
		folder.add(file);
		
		folder.add(subfolder);
		folder.add(subfolder);
		
		TreeSet<Node> nodes = folder.getNodes();
		
		assertFalse(nodes.isEmpty());
		assertEquals(2, nodes.size());
		assertSame(subfolder, nodes.first());
		assertSame(file, nodes.last());
	}
	
	@Test
	public void should_print_empty_if_no_file_inside_a_folder() {
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
		folder.add(new File("Wheel"));
		
		Folder subfolder1 = new Folder("Seat");
		subfolder1.add(new File("Cat"));
		subfolder1.add(new File("Ant"));
		
		Folder subfolder2 = new Folder("Engine");
		subfolder2.add(new File("Piston"));
		subfolder2.add(new File("Cylinder"));
		
		folder.add(subfolder1);
		folder.add(subfolder2);
		
		folder.tree();	
		
		String output = outputStreamCaptor.toString().trim();
		
		String expected = new StringBuilder()
				.append("Engine")
				.append(System.lineSeparator())
				.append("Cylinder")
				.append(System.lineSeparator())
				.append("Piston")
				.append(System.lineSeparator())
				.append("Seat")
				.append(System.lineSeparator())
				.append("Ant")
				.append(System.lineSeparator())
				.append("Cat")
				.append(System.lineSeparator())
				.append("Wheel")
				.toString();
		
		assertEquals(expected, output);
	}
	
	
}
