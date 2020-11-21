package com.eagle.interview.IKM;

public class Q10 {
	static int total = 10;
	public void call(){
		int total = 5;
		System.out.println(this.total);
	}
	public static void main(String[] args) {
		Q10 a = new Q10();
		a.call();
	}
}
