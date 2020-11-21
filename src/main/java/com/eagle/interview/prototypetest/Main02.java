package com.eagle.interview.prototypetest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main02 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
		MyService service = (MyService) ac.getBean("myService");
		/**
		 * 输出1 3 6
		 */
		service.test(1);
		service.test(2);
		service.test(3);

		/**
		 * 输出1 2 3
		 */
		service.test2(1);
		service.test2(2);
		service.test2(3);


	}
}
