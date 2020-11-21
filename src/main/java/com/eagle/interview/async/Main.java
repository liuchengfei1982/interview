package com.eagle.interview.async;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
		AsyncService bean = ac.getBean(AsyncService.class);
		bean.testAsync();
		System.out.println("main函数执行完成");
	}
}
