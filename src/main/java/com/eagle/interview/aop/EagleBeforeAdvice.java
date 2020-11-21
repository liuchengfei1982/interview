package com.eagle.interview.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class EagleBeforeAdvice implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("before invoke method [" + method.getName() + "],aop before logic invoked");
	}
}
