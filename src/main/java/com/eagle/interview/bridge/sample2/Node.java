package com.eagle.interview.bridge.sample2;

public class Node<T> {
	public T data;

	public Node(T data) {
		this.data = data;
	}

	public void setData(T data) {
		System.out.println("Node.setData");
		this.data = data;
	}

	public T getData() {
		System.out.println("Node.getData");
		return this.data;
	}
}
