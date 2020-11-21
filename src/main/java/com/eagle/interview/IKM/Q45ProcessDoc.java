package com.eagle.interview.IKM;

/**
 * {...} 是普通代码块，在构造函数的时候执行
 * static{...} 静态代码块，在调用main函数时候，就会执行
 */
class Q45ProcessDoc extends  Q45EmailDoc{
	public static void main(String[] args) {
		System.out.println("Pre process document before send."); //3
		new Q45ProcessDoc();
		System.out.println("Document has been processed and send."); //8
	}
}
