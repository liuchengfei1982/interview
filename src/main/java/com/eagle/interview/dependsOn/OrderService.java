package com.eagle.interview.dependsOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// OrderService中需要注入EagleService
@Component
public class OrderService {
	@Autowired
	EagleService eagleService;

	public void print(){
		System.out.println(eagleService.test());
	}

}
