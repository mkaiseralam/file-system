package com.kaiser.file;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.kaiser.iterator.CompositeIterator;

public class Folder extends Node {
	private List<Node> nodes;
	
	public static final String EXCEPTION_MESSAGE_FOLDER_NAME = "Folder name can not be null or empty";
	public static final String EXCEPTION_MESSAGE_ADD_OPERATION = "File/folder can not be null";
	
	/**
	 * <p>This will create a folder node  with name.</p>
	 * @param String name of the file
	 * @return {@link Folder}
	 * @throws IllegalArgumentException if name is null or empty
	 */
	public Folder(String name) {
		super(validate(name));
		this.nodes = new ArrayList<>();
	}
	
	/**
	 * <p>This will return {@link Iterator} for it's children traversal.
	 * return {@link CompositeIterator}</p>
	 * @return {@link Iterator}<Node>
	 */
	@Override
	public Iterator<Node> createIterator() {
		return new CompositeIterator(nodes.iterator());
	}
	
	/**
	 * <p>Get the size of the content of a folder.</p>
	 * @return the size of all files and folders inside it
	 */
	@Override
	public int size() {
		return nodes.parallelStream()
				.reduce(0, (partialSizeResult, node) -> partialSizeResult + node.size(), Integer::sum);
	}
	
	/**
	 * <p>Add a {@link File} / {@link Folder} to a folder.</p>
	 * @throws NullPointerException if @param node is null
	 */
	public void add(Node node) {
		Objects.requireNonNull(node, EXCEPTION_MESSAGE_ADD_OPERATION);
		
		nodes.add(node);
	}
	
	/**
	 * <p>This will print on standard output stream the names
	 *  of all files in a folder sorted alphabetically,
	 *  one each line. This excludes sub-folders.</p>
	 */
	public void list() {
		nodes.stream()
			.filter(n -> n instanceof File)
			.sorted(Comparator.comparing(Node::getName))
			.map(n -> n.getName())
			.forEach(System.out::println);
	}
	
	/**
	 * <p><b>Walk the tree of a folder including all of its sub-folders.</b>
	 * This will print on standard output stream the names of all files 
	 * and folders in that folder tree. This includes sub-folders.</p>
	 */
	public void tree() {
		Iterator<Node> iterator = nodes.iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next();
			System.out.println(node.getName());
			if (node instanceof Folder) {
				((Folder) node).tree();
			}
		}
	}
	
	private static String validate(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE_FOLDER_NAME);
		}
		return name;
	}
}
