package com.kaiser.iterator;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import com.kaiser.file.File;

public class TreeSetDirectoryTest {
	
	@Test
	public void should_return_iterator_on_create_iterator() {
		Directory nodes = new TreeSetDirectory();
		
		assertTrue(nodes.createIterator() instanceof Iterator);
	}
	
	@Test
	public void should_add_item() {
		Directory nodes = new TreeSetDirectory();
		
		nodes.add(new File("test"));
		
		assertTrue(nodes.createIterator().hasNext());
	}
}
