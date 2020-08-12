package com.kaiser.file;

import java.util.Iterator;

import com.kaiser.iterator.Directory;
import com.kaiser.iterator.TreeSetDirectory;

public class Folder extends Node {
	private Directory nodes;
	
	public Folder(String name) {
		super(name);
		this.nodes = new TreeSetDirectory();
	}
	
	public void add(Node node) {
		nodes.add(node);
	}
	
	public Iterator<Node> getNodes() {
		return nodes.createIterator();
	}
	
	public void list() {
		Iterator<Node> iterator = getNodes();
		
		while (iterator.hasNext()) {
			Node node = iterator.next();
			if (node instanceof File) {
				System.out.println(node.getName());
			}
		}
	}
	
	public void tree() {
		Iterator<Node> iterator = getNodes();
		
		while (iterator.hasNext()) {
			Node node = iterator.next();
			System.out.println(node.getName());
			if (node instanceof Folder) {
				((Folder) node).tree();
			}
		}
	}
}
