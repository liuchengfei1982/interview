package com.eagle.interview.IKM;

public class Q15 extends Q15Parent {
	public Q15(){
		count++;
	}

	public static void main(String[] args) {
		System.out.println("Count:"+ getCount());
		Q15 child = new Q15();
		System.out.println("Count:"+ getCount());
	}
}
