package com.eagle.interview.async;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Component  // 这个类本身要被Spring管理
public class AsyncService {
	@Async  // 添加注解表示这个方法要异步执行
	public void testAsync(){
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("testAsync invoked");
	}

//	@Bean(name = "threadPoolTaskExecutor")
//	public Executor threadPoolTaskExecutor() {
//		return new ThreadPoolTaskExecutor();
//	}
}
