package com.eagle.interview.IKM;

public class Q15Parent {
	protected static int count = 0;
	public Q15Parent(){
		count++;
	}
	static int getCount(){return count;}
}
