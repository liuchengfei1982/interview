package com.eagle.interview.aop.proxyfactorybean;

import com.eagle.interview.main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SourceMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext cc =
				new ClassPathXmlApplicationContext("application-init.xml");
		EagleService eagleProxy = ((EagleService) cc.getBean("eagleProxy"));
		eagleProxy.testAop();
	}
}
