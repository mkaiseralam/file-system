package com.kaiser.file;

import java.util.Iterator;
import java.util.Objects;

import com.kaiser.iterator.NullIterator;

public class File extends Node {
	private String content;
	
	public static final String FILE_NAME_EXCEPTION_MESSAGE = "File name can not be null or empty";
	public static final String FILE_WRITE_EXCEPTION_MESSAGE = "Content can not be null";

	/**
	 * <p>This will create a file node  with name.</p>
	 * @param String name of the file
	 * @return {@link File}
	 * @throws IllegalArgumentException if name is null or empty
	 */
	public File (String name) {
		super(validateFileName(name));
		this.content = "";
	}
	
	/**
	 * <p>Get the size of the content of the file</p>
	 * @return the size of the content
	 */
	@Override
	public int size() {
		return content.length();
	}

	/**
	 * <p>This will return iterator for it's children traversal.
	 * Since file is a leaf node it won't have any children, it will
	 * return {@link NullIterator}</p>
	 * @return {@link Iterator}<Node>
	 */
	@Override
	public Iterator<Node> createIterator() {
		return new NullIterator();
	}
	
	/**
	 * <p>Write a string content to a file.
	 * After creating an empty file, you can write some content to it.
	 * It will also write on existing file by appending content</p>
	 * @param content String
	 * @throws NullPointerException if content is null
	 */	
	public void write(String content) {
		Objects.requireNonNull(content, FILE_WRITE_EXCEPTION_MESSAGE);
		
		this.content = new StringBuilder()
					.append(this.content)
					.append(content)
					.toString();
	}
	
	/**
	 * <p>Read string content from file</p>
	 * @return content String
	 */	
	public String read() {
		return content;
	}
	
	/**
	 * <p>Clear the content of the file</p>
	 */	
	public void clear() {
		content = "";
	}
	
	private static String validateFileName(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException(FILE_NAME_EXCEPTION_MESSAGE);
		}
		return name;
	}
}
