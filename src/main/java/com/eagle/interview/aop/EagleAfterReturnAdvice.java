package com.eagle.interview.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public class EagleAfterReturnAdvice implements AfterReturningAdvice{
	@Override
	public void afterReturning(@Nullable Object returnValue, Method method, Object[] args, @Nullable Object target) throws Throwable {
		System.out.println("after invoke method [" + method.getName() + "],aop afterReturning logic invoked");
	}
}
