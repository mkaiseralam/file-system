package com.kaiser.file;

public class File extends Node {
	private String content;

	public File (String name) {
		super(name);
		this.content = "";
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
	
	public int size() {
		return content.length();
	}
}
