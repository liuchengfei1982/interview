package com.eagle.interview.IKM;

/**
 * {...} 是普通代码块，在构造函数的时候执行
 * static{...} 静态代码块，在调用main函数时候，就会执行
 */
class Q45EmailDoc extends Q45SendDoc {
	static {
		System.out.println("In MailDocument."); //1
	}
	public Q45EmailDoc(){
		System.out.println("Start to mail document.");
	} //7
	{
		System.out.println("MailDocument is in progress."); //6
	}
	static {
		System.out.println("MailDocument is complete."); //2
	}
}
