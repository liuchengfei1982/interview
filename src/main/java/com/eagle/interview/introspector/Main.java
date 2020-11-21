package com.eagle.interview.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class Main {
	public static void main(String[] args) throws Exception{
		BeanInfo beanInfo = Introspector.getBeanInfo(People.class);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			System.out.print(propertyDescriptor.getName()+"   ");
		}
	}
	// 程序输出：age   class   name
	// 为什么会输出class呢？前文中有提到，“看到getName()，内省就会认为这个类中
	// 有name字段，但事实上并不一定会有name”，
	// 我们知道每个对象都会有getClass方法，所以使用内省时，
	// 默认就认为它具有class这个字段
}
