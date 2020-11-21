package com.eagle.interview.configurationtest;

import org.springframework.stereotype.Component;

@Component
public class EagleSvc {
	public EagleSvc(){
		System.err.println("create eagleSvc");
	}
}