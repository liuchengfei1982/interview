package com.eagle.interview.configurationtest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
		EagleSvc bean = ac.getBean(EagleSvc.class);
		System.out.println(bean);
	}
}