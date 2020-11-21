package com.eagle.interview.configurationtest;

import org.springframework.stereotype.Component;

@Component
public class OtherSvc {
	public OtherSvc(){
		System.err.println("create otherSvc");
	}
}