package com.eagle.interview.aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;

public class EaglePointcut implements Pointcut {
	@Override
	@NonNull
	public ClassFilter getClassFilter() {
		// 在类级别上不进行拦截
		return ClassFilter.TRUE;
	}

	@Override
	@NonNull
	public MethodMatcher getMethodMatcher() {
		return new StaticMethodMatcherPointcut() {
			@Override
			public boolean matches(@NonNull Method method, Class<?> targetClass) {
				// 对于toString方法不进行拦截
				return !method.getName().equals("toString");
			}
		};
	}
}
