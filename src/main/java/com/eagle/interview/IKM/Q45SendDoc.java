package com.eagle.interview.IKM;

/**
 * {...} 是普通代码块，在构造函数的时候执行
 * static{...} 静态代码块，在调用main函数时候，就会执行
 */
class Q45SendDoc {
	{
		System.out.println("In SendDocument."); //4
	}
	public Q45SendDoc(){
		System.out.println("Start to send document."); //5
	}
}
