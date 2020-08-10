package com.kaiser.file;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Folder extends Document {
	private List<Document> childDocuments;
	
	public Folder(String name) {
		super(name);
		this.childDocuments = new ArrayList<Document>();
	}
	
	@Override
	public int size() {
		return 0;
	}
	
	public void add(Document document) {
		childDocuments.add(document);
	}
	
}
