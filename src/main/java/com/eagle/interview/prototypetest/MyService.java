package com.eagle.interview.prototypetest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyService implements ApplicationContextAware {
	@Autowired
	private LuBanService luBanService;

	private ApplicationContext applicationContext;

	public void test(int a){
		luBanService.addAndPrint(a);
	}

	public void test2(int a){
		LuBanService luBanService = ((LuBanService) applicationContext.getBean("luBanService"));
		luBanService.addAndPrint(a);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
