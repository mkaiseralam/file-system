package com.kaiser.file;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.kaiser.iterator.CompositeIterator;

public class Folder extends Node {
	private List<Node> nodes;
	
	public Folder(String name) {
		super(name);
		this.nodes = new ArrayList<>();
	}
	
	@Override
	public Iterator<Node> createIterator() {
		return new CompositeIterator(nodes.iterator());
	}
	
	@Override
	public int size() {
		return nodes.parallelStream()
				.reduce(0, (partialSizeResult, node) -> partialSizeResult + node.size(), Integer::sum);
	}
	
	public void add(Node node) {
		nodes.add(node);
	}
	
	public void list() {
		nodes.stream()
			.filter(n -> n instanceof File)
			.sorted(Comparator.comparing(Node::getName))
			.map(n -> n.getName())
			.forEach(System.out::println);
	}
	
	public void tree() {
		Iterator<Node> iterator = createIterator();
		
		while (iterator.hasNext()) {
			Node node = iterator.next();
			System.out.println(node.getName());
		}
	}
}
