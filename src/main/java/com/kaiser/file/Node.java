package com.kaiser.file;

import java.util.Iterator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Node {
	protected String name;
	
	public abstract int size();
	
	public abstract Iterator<Node> createIterator();
}
