package com.eagle.interview.aop.proxyfactorybean;

public class EagleService {
	@Override
	public String toString() {
		System.out.println("eagleService toString invoke");
		return "eagleService";
	}

	public void testAop(){
		System.out.println("testAop invoke");
	}
}
