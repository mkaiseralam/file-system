package com.kaiser.file;

public class File extends Document {
	private String content;

	public File (String name) {
		super(name);
		this.content = "";
	}
	
	@Override
	public int size() {
		return 0;
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
}
