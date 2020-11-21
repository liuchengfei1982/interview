package com.eagle.interview.IKM;

/**
 * 基本类型
 */
public class Q1 {
	public static void main(String[] args) {
		int i = 1000000000;  //java中int的取值范围为-2147483648到+-2147483648。-2的31次方 2的31次方-1
		long l = 1000000000; //2的63次方
		short s = 10000; //-2的15此方 2的15次方-1
		float f = 123.45566778465651454545f; //2的-149次方 2的128次方-1,float的小数点后6位
		double d = 3.1234567890123456789; //2的-1074次方 2的1024次方-1,double的小数点后16位。

		System.out.println(i);
		System.out.println(l);
		System.out.println(s);
		System.out.println(f);
		System.out.println(d);
	}
}
