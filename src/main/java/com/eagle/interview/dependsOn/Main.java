package com.eagle.interview.dependsOn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//明确的表示了EagleFactoryBean是依赖于orderService的，
//所以必定会先创建orderService再创建EagleFactoryBean
//EagleService没有通过注解的方式将它放到容器中，
//而是通过上面的EagleFactoryBean来管理对应的Bean
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
		OrderService bean = ac.getBean(OrderService.class);
		bean.print();
	}
}
