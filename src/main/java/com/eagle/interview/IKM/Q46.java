package com.eagle.interview.IKM;

public class Q46 {
	private static int count;
	static {
		System.out.println("In block 1");
		count =10;
	}
	private int[] data;
	{
		System.out.println("In block 2");
		data = new int[count];
		for (int i = 0; i < count; i++) {
			data[i] = i;
		}
	}

	public static void main(String[] args) {
		System.out.println("Count= "+count);
		System.out.println("Before 1st call to new");
		Q46 test =  new Q46();
		System.out.println("Before 2rd call to new");
		Q46 test2 =  new Q46();
	}
}
