package com.eagle.interview.bridge.sample2;

public class MyNode extends Node<Integer> {
	public MyNode(Integer data) { super(data); }

	@Override
	public void setData(Integer data) {
		System.out.println("MyNode.setData");
		super.setData(data);
	}

	// 子类对新增的那个方法进行复写
	@Override
	public Integer getData() {
		System.out.println("MyNode.getData");
		return super.getData();
	}
}
