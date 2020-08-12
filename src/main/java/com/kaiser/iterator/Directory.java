package com.kaiser.iterator;

import java.util.Iterator;

import com.kaiser.file.Node;

public interface Directory {
	Iterator<Node> createIterator();
	
	void add(Node node);
}
