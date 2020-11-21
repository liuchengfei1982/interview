package com.eagle.interview.dependsOn;

// 没有通过注解的方式将它放到容器中，而是通过上面的EagleFactoryBean来管理对应的Bean
public class EagleService {
	public String test(){
		return "test EagleService";
	}
}
