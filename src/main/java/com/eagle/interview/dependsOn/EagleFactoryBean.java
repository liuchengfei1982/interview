package com.eagle.interview.dependsOn;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

// 这里申明了一个FactoryBean,并且通过@DependsOn注解申明了这个FactoryBean的
// 创建要在orderService之后，主要目的是为了在DmzFactoryBean创建前让容器发生一次属性注入
@Component
@DependsOn("orderService")
public class EagleFactoryBean implements FactoryBean<EagleService> {
	@Override
	public EagleService getObject() throws Exception {
		return new EagleService();
	}

	@Override
	public Class<?> getObjectType() {
		return EagleService.class;
	}
}
