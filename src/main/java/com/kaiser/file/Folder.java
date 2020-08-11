package com.kaiser.file;

import java.util.Comparator;
import java.util.TreeSet;

public class Folder extends Node {
	private TreeSet<Node> nodes;
	
	public Folder(String name) {
		super(name);
		this.nodes = new TreeSet<>(Comparator.comparing(Node::getName));
	}
	
	public void add(Node node) {
		nodes.add(node);
	}
	
	public TreeSet<Node> getNodes() {
		return nodes;
	}
	
	public void list() {
		nodes.stream()
			.filter(d -> d instanceof File)
			.map(d -> d.getName())
			.forEach(System.out::println);
	}
	
	public void tree() {
		nodes.forEach(node -> {
			System.out.println(node.getName());
			if (node instanceof Folder) {
				Folder folder = (Folder) node;
				folder.tree();
			}
		});
	}
}
