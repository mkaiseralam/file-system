package com.kaiser.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FileTest {
	@Test
	public void should_create_file() {
		File file = new File("Pie");
		
		assertNotNull(file);
		assertTrue(file instanceof Node);
	}
	
	@Test
	public void should_write_string_to_file() {
		File file = new File("Pie");
		
		file.write("Hello, World!");
		
		assertFalse(file.read().isEmpty());
	}
	
	@Test
	public void should_read_string_from_file() {
		File file = new File("Pie");	
		String content = "Hello, World!";
		file.write(content);
		
		assertEquals(content, file.read());
	}
	
	@Test
	public void should_read_string_of_multiple_write_from_file() {
		File file = new File("Pie");	
		String content1 = "Hello, World!";
		String content2 = "I am second content";
		file.write(content1);
		file.write(content2);
		
		assertEquals(content1 + content2, file.read());
	}
	
	@Test
	public void should_read_empty_string_if_no_content_in_the_file() {
		File file = new File("Pie");	
		
		assertEquals("", file.read());
	}
	
	@Test
	public void should_clear_the_content_of_the_file() {
		File file = new File("Pie");	
		String content = "Hello, World!";
		file.write(content);
		
		assert(!file.read().isEmpty());
		
		file.clear();
		
		assertTrue(file.read().isEmpty());
	}
	
	@Test
	public void should_return_size_of_the_content_of_the_file() {
		File file = new File("Pie");	
		String content = "Hello, World!";
		file.write(content);

		assertEquals(content.length(), file.size());
	}
	
	@Test
	public void should_return_size_of_the_content_zero_if_no_content_is_written_to_the_file() {
		File file = new File("Pie");	

		assertEquals(0, file.size());
	}
}
