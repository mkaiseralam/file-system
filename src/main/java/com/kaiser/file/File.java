package com.kaiser.file;

import java.util.Iterator;

import com.kaiser.iterator.NullIterator;

public class File extends Node {
	private String content;

	public File (String name) {
		super(name);
		this.content = "";
	}
	
	@Override
	public int size() {
		return content.length();
	}

	@Override
	public Iterator<Node> createIterator() {
		return new NullIterator();
	}
	
	public void write(String content) {
		this.content = new StringBuilder()
					.append(this.content)
					.append(content)
					.toString();
	}
	
	public String read() {
		return content;
	}
	
	public void clear() {
		content = "";
	}
}
