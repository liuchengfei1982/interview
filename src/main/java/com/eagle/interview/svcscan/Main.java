package com.eagle.interview.svcscan;

import com.eagle.interview.async.AsyncService;
import com.eagle.interview.async.Config;
import com.eagle.interview.svcscan.impl.RouteService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.List;

public class Main {
	public static void main(String[] args) {
//		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ScanConfig.class);
//		RouteService bean = ac.getBean(RouteService.class);
//		bean.printRoute();
//		System.out.println("main函数执行完成");

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("com.eagle.interview.svcscan");
		RouteService bean = ac.getBean(RouteService.class);
		bean.printRoute();
		System.out.println("main函数执行完成");
	}


}
