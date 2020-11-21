package com.eagle.interview.configurationtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//不配置的Configuration的话，EagleSvc创建被创建了两次
@Configuration
@ComponentScan("com.eagle.interview.configurationtest")
public class Config{
	@Bean
	public A a(){
		//第二次创建是Spring容器在创建a时调用了a()，而a()
		// 又调用了eagleSvc()方法
		return new A(eagleSvc());
	}

	//第一次创建是被Spring容器所创建的，Spring调用这个eagleSvc()
	// 创建了一个Bean被放入了单例池中
	@Bean
	public EagleSvc eagleSvc(){
		return new EagleSvc();
	}
}



