package com.mcf.davidee.nbtedit.nbt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Node<T> {

	private final List<Node<T>> children = new ArrayList<>();

	private Node<T> parent;
	private T obj;

	private boolean drawChildren;

	public boolean shouldDrawChildren() {
		return this.drawChildren;
	}

	public void toggleDrawChildren() {
		this.drawChildren = !this.drawChildren;
	}

	public void setDrawChildren(boolean draw) {
		this.drawChildren = draw;
	}

	private Node(Node<T> parent, T obj) {
		this.parent = parent;
		this.obj = obj;
	}

	public Node<T> parent(Node<T> parent) {
		this.parent = parent;
		return this;
	}

	public static <T> Node<T> of(Node<T> parent) {
		return of(parent, null);
	}

	public static <T> Node<T> of(T obj) {
		return of(null, obj);
	}

	public static <T> Node<T> of(Node<T> parent, T obj) {
		return new Node<T>(parent, obj);
	}

	@SafeVarargs
	public final Node<T> children(Node<T>... n) {
		return this.children(Arrays.asList(n));
	}

	public Node<T> children(Collection<? extends Node<T>> n) {
		n.forEach(node -> node.parent(this));
		this.children.addAll(n);
		return this;
	}

	public Node<T> removeChild(Node<T> n) {
		this.children.remove(n);
		return this;
	}

	public List<Node<T>> children() {
		return this.children;
	}

	public Node<T> parent() {
		return this.parent;
	}

	public T object() {
		return this.obj;
	}

	@Override
	public String toString() {
		return "" + this.obj;
	}

	public boolean hasChildren() {
		return !this.children.isEmpty();
	}

	public boolean hasParent() {
		return this.parent != null;
	}
}
