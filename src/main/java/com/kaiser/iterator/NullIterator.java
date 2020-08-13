package com.kaiser.iterator;

import java.util.Iterator;

import com.kaiser.file.Node;

/**
 *  an iterator that is a "no op".
 *  Implements {@link Iterator} to get leverage
 */
public class NullIterator implements Iterator<Node> {
	
	public Node next() {
		return null;
	}
  
	public boolean hasNext() {
		return false;
	}
}
