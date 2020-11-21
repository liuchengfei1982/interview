package com.eagle.interview.IKM;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Function中
 * compose 和 andThen 的不同之处是函数执行的顺序不同。
 * compose 函数先执行参数，然后执行调用者，
 * andThen 先执行调用者，然后再执行参数
 *
 */
public class Q21Function {
	public static void main(String[] args) {
		Function<Boolean, String> booleaner = i -> Boolean.toString(i); //Function接口只有apply一个实现方法
		Function<String,Boolean> stringer = s -> Boolean.parseBoolean(s);


		System.out.println(stringer.compose(booleaner).apply(true)); //true
		System.out.println(booleaner.apply(true)); //true
		/**
		 * compose先执行参数stringer,然后调用apply("true")，得到boolean结果
		 *
		 * 再执行booleaner，因此将boolean结果传入，然后输出String
		 */
		System.out.println(booleaner.compose(stringer).apply("true").substring(0,3)); //true

		/**
		 * compose先执行参数booleaner，再执行stringer，因此传boolean
		 */
//		System.out.println(booleaner.andThen(stringer).apply("true")); //报错

//		functionDemo();
		functionDemo2();
		biFunctionDemo();
	}

	/**
	 * ① Function 接口是一个功能型接口，是一个转换数据的作用。
	 * ② Function 接口实现 apply 方法来做转换。
	 */
	public static void functionDemo(){
		//① 使用map方法，泛型的第一个参数是转换前的类型，第二个是转化后的类型
		Function<String, Integer> function = new Function<String, Integer>() {
			@Override
			public Integer apply(String s) {
				return s.length();//获取每个字符串的长度，并且返回
			}
		};

		Stream<String> stream = Stream.of("aaa", "bbbbb", "ccccccv");
		Stream<Integer> stream1 = stream.map(function);
		stream1.forEach(System.out::println);

		System.out.println("********************");

	}

	public static void functionDemo2(){
		Function<Integer, Integer> times2 = e -> e * 2;
		Function<Integer, Integer> squared = e -> e * e;

		final Integer result = times2.compose(squared).apply(4);//32
		final Integer result2 = times2.andThen(squared).apply(4);//64

		System.out.println(result);
		System.out.println(result2);

	}

	public static void biFunctionDemo(){
		final int result1 = compute3(2, 3, (v1, v2) -> v1 + v2);
		final int result2 = compute3(2, 3, (v1, v2) -> v1 - v2) ;
		final int result3 = compute3(2, 3, (v1, v2) -> v1 * v2);
		// 2+3, 5作为参数传进去5*5
		final int result4 = compute4(2, 3, (v1, v2) -> v1 + v2, v1 -> v1 * v1);

		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
	}
	public static int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {

		return biFunction.apply(a, b);
	}

	/**
	 * BiFunction没有compose方法
	 * 如果有compose方法的话，那就是先执行Function的apply方法，
	 * 但是执行完毕后只返回一个参数，
	 * 而BiFunction需要两个参数，所以肯定是不行的
	 *
	 * @param a
	 * @param b
	 * @param biFunction
	 * @param function
	 * @return
	 */
	public static int compute4(int a, int b, BiFunction<Integer, Integer, Integer> biFunction,Function<Integer, Integer> function) {
		return biFunction.andThen(function).apply(a, b);
	}
}
