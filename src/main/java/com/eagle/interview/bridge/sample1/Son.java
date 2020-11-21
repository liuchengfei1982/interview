package com.eagle.interview.bridge.sample1;

public class Son extends Parent {
	// 这里对父类的方法进行了重写，但是返回值类型跟父类中不一样，父类中的返回值类型为Number,子类中的返回值类型为Integer，Integer是Number的子类
	@Override
	public Integer get(Number number) {
		System.out.println("son's method invoke");
		return 2;
	}
}