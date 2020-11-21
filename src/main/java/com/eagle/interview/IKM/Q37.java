package com.eagle.interview.IKM;

/**
 * 　>> 右移:
 *		负数右移用1补位
 *		正数右移用0补位
 *
 * 	>>> 无符号右移:
 *		无论正负，都用0补位
 */
public class Q37 {
	public static void main(String[] args) {
		int x = -1;
		System.out.println(x>>>32); //超出范围，相当没移动到，还是原样
		System.out.println(x>>32);
		System.out.println(x>>>31);
		System.out.println(x>>1);
		System.out.println(x>>>0);
	}
}
