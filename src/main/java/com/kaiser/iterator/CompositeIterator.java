package com.kaiser.iterator;

import java.util.Iterator;
import java.util.Stack;

import com.kaiser.file.Node;

/**
 *  It’s got the job of iterating over the Folder in the node,'
 *  and of making sure all the child files (and child child folder, and so on) are included.
 *  Implements {@link Iterator} which will get leverage
 */
public class CompositeIterator implements Iterator<Node> {
	Stack<Iterator<Node>> stack = new Stack<Iterator<Node>>();
	
	public CompositeIterator(Iterator<Node> iterator) {
		stack.push(iterator);
	}
	
	@Override
	public boolean hasNext() {
		if (stack.empty()) {
			return false;
		} else {
			Iterator<Node> iterator = stack.peek();
			if (!iterator.hasNext()) {
				stack.pop();
				return hasNext();
			} else {
				return true;
			}
		}
	}

	@Override
	public Node next() {
		if (hasNext()) {
			Iterator<Node> iterator = stack.peek();
			Node node = iterator.next();
			stack.push(node.createIterator());
			return node;
		} else {
			return null;
		}
		
	}
	

}
