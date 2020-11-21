package com.eagle.interview.IKM;


/**
 * 8bit(位)=1Byte(字节)
 * 1024Byte(字节)=1KB
 * 1024KB=1MB
 * 1024MB=1GB
 */
public class Q8Jvm {
	public static void main(String[] args) {
		//Current JVM Heap Size 16252928byte = 16MB
		System.out.println(Runtime.getRuntime().totalMemory());
		// Max JVM Heap Size 259522560byte = 256MB
		System.out.println(Runtime.getRuntime().maxMemory());
		// Free JVM Heap Size 14877760byte = 14MB
		System.out.println(Runtime.getRuntime().freeMemory());



	}
}
