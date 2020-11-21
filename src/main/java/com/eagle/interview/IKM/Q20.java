package com.eagle.interview.IKM;

public class Q20 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("buffering");
		sb.replace(2,7,"BUFFER"); //从0开始，7的前一位
		System.out.println(sb);
		sb.delete(2,4);//从0开始，4的前一位
		System.out.println(sb);
		final String s = sb.substring(1, 5); //从0开始，5的前一位
		System.out.println(s);
	}
}
