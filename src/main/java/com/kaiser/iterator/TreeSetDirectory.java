package com.kaiser.iterator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import com.kaiser.file.Node;

public class TreeSetDirectory implements Directory {
	private TreeSet<Node> nodes;
	
	public TreeSetDirectory() {
		this.nodes = new TreeSet<>(Comparator.comparing(Node::getName));
	}
	
	@Override
	public Iterator<Node> createIterator() {
		return nodes.iterator();
	}

	@Override
	public void add(Node node) {
		nodes.add(node);
	}
}
