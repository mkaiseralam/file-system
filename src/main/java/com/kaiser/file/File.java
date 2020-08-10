package com.kaiser.file;

public class File extends Document {
	private String content;

	public File (String name) {
		super(name);
	}
	
	@Override
	public int size() {
		return 0;
	}
	
	public void write(String content) {
		this.content = content;
	}
	
	public String read() {
		return content;
	}
}
