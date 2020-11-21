package com.eagle.interview.aop;

public class EagleService {
	@Override
	public String toString() {
		System.out.println("eaglezService toString invoke");
		return "eagleService";
	}

	public void testAop(){
		System.out.println("testAop invoke");
	}
}
