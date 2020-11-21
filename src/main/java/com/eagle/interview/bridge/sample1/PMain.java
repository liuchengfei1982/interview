package com.eagle.interview.bridge.sample1;

import java.lang.reflect.Method;

/**
 * 子类对父类的方法进行了重写，并且子类方法中的返回值类型跟父类方法的返回值
 * 类型不一样
 */
public class PMain {
	public static void main(String[] args) {
		Son son = new Son();
		Method[] declaredMethods = son.getClass().getDeclaredMethods();
		for (int i = 0; i < declaredMethods.length; i++) {
			Method declaredMethod = declaredMethods[i];
			String methodName = declaredMethod.getName();
			Class<?> returnType = declaredMethod.getReturnType();
			Class<?> declaringClass = declaredMethod.getDeclaringClass();
			boolean bridge = declaredMethod.isBridge();
			System.out.print("第" + (i+1) + "个方法名称：" + methodName + "，方法返回值类型：" + returnType + "  ");
			System.out.print(bridge ? " 是桥接方法" : " 不是桥接方法");
			System.out.println("  这个方法是在"+declaringClass.getSimpleName()+"上申明的");
		}
	}
}
