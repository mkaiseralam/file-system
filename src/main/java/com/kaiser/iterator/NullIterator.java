package com.kaiser.iterator;

import java.util.Iterator;

import com.kaiser.file.Node;

public class NullIterator implements Iterator<Node> {
	
	public Node next() {
		return null;
	}
  
	public boolean hasNext() {
		return false;
	}
}
