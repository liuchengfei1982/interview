package com.eagle.interview.bridge.sample2;

import com.eagle.interview.bridge.sample1.Son;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 子类对父类的方法进行了重写，并且子类方法中的返回值类型跟父类方法的返回值
 * 类型不一样
 */
public class PMain {
	public static void main(String[] args) {
		MyNode mn = new MyNode(5);
		Method[] declaredMethods = mn.getClass().getDeclaredMethods();
		for (int i = 0; i < declaredMethods.length; i++) {
			Method declaredMethod = declaredMethods[i];
			String methodName = declaredMethod.getName();
			Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
			Class<?> declaringClass = declaredMethod.getDeclaringClass();
			boolean bridge = declaredMethod.isBridge();
			System.out.print("第" + (i + 1) + "个方法名称：" + methodName + "，参数类型：" + Arrays.toString(parameterTypes) + "  ");
			System.out.print(bridge ? " 是桥接方法" : " 不是桥接方法");
			System.out.println("  这个方法是在" + declaringClass.getSimpleName() + "上申明的");
		}
	}
}
