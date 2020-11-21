package com.eagle.interview.testsupper;

public class AppContext extends GenericAppContext {
	public AppContext(){
		System.out.println("AppContext..");
	}
	public AppContext(String s){
		this();
		System.out.println(s);
	}
}
