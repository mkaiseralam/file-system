package com.kaiser;

import com.kaiser.file.File;
import com.kaiser.file.Folder;

public class Main {

	public static void main(String[] args) {
		
		/**
		 * Create a folder
		 */
		Folder folder = new Folder("Apple");
		
		/**
		 * Create a file
		 */
		File file = new File("Pie");	
		
		/**
		 * Add a file to a folder
		 */
		folder.add(file);
		
		/**
		 * Add a folder to a folder
		 */
		folder.add(new Folder("Drinks"));
		
		/**
		 * Write a string content to a file
		 */
		file.write("Hello, World!");
		
		/**
		 * Read string content from file
		 */
		String content = file.read();

	}

}
