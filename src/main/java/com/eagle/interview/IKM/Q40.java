package com.eagle.interview.IKM;

public class Q40 implements Q40I2{
	public static void main(String[] args) {
		System.out.println(Q40I2.name+",");
		System.out.println(Q40I2.s1+",");
		System.out.println(((Q40I1) new Q40()).name);
	}
}
