package com.kaiser.file;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class FolderTest {
	
	@Test
	public void should_create_folder() {
		Folder folder = new Folder("Apple");
		
		assertNotNull(folder);
		assertTrue(folder instanceof Document);
	}
	
	@Test
	public void should_add_file_to_folder() {
		Folder folder = new Folder("Apple");
		File file = new File("Pie");
		
		folder.add(file);
		
		List<Document> documents = folder.getDocuments();
		
		assertFalse(documents.isEmpty());
		assertSame(file, documents.get(0));
	}
	
	@Test
	public void should_add_folder_to_folder() {
		Folder folder = new Folder("Apple");
		Folder subforlder = new Folder("Peach");
		
		folder.add(subforlder);
		
		List<Document> documents = folder.getDocuments();
		
		assertFalse(documents.isEmpty());
		assertSame(subforlder, documents.get(0));
	}
}
