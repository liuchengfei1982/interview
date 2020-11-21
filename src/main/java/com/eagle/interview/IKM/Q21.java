package com.eagle.interview.IKM;

import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Java8之Consumer、Supplier、Predicate和Function攻略
 *
 */
public class Q21 {
	public static void main(String[] args) {
		Predicate<String> lengther = (s) -> s.length() <2; //Predicate接口只有test一个实现方法
		Predicate<String> equalizer = Predicate.isEqual("car");
		Function<Boolean, String> booleaner = i -> Boolean.toString(i); //Function接口只有apply一个实现方法
		Function<String,Boolean> stringer = s -> Boolean.parseBoolean(s);


		System.out.println(stringer.compose(booleaner).apply(true)); //true
		System.out.println(booleaner.apply(true)); //true

//		negate就是将当前条件取反!false or false
		System.out.println(lengther.negate().or(equalizer).test("CAR")); //true

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
	}

	/**
	 *
	 * ① Consumer是一个接口，并且只要实现一个 accept 方法，就可以作为一个“消费者”输出信息。
	 * ② 其实，lambda 表达式、方法引用的返回值都是 Consumer 类型，所以，他们能够作为 forEach 方法的参数，并且输出一个值。
	 */
	public static void consumerDemo(){
		//① 使用consumer接口实现方法
		Consumer<String> consumer = new Consumer<String>() {

			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};
		Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
		stream.forEach(consumer);

		System.out.println("********************");

		//② 使用lambda表达式，forEach方法需要的就是一个Consumer接口
		stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
		Consumer<String> consumer1 = (s) -> System.out.println(s);//lambda表达式返回的就是一个Consumer接口
		stream.forEach(consumer1);
		//更直接的方式
		//stream.forEach((s) -> System.out.println(s));
		System.out.println("********************");

		//③ 使用方法引用，方法引用也是一个consumer
		stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
		Consumer consumer2 = System.out::println;
		stream.forEach(consumer);
		//更直接的方式
		//stream.forEach(System.out::println);
	}

	/**
	 * ① Supplier 接口可以理解为一个容器，用于装数据的。
	 * ② Supplier 接口有一个 get 方法，可以返回值。
	 */
	public static void supplierDemo(){
		//① 使用Supplier接口实现方法,只有一个get方法，无参数，返回一个值
		Supplier<Integer> supplier = new Supplier<Integer>() {
			@Override
			public Integer get() {
				//返回一个随机值
				return new Random().nextInt();
			}
		};

		System.out.println(supplier.get());

		System.out.println("********************");

		//② 使用lambda表达式，
		supplier = () -> new Random().nextInt();
		System.out.println(supplier.get());
		System.out.println("********************");

		//③ 使用方法引用
		Supplier<Double> supplier2 = Math::random;
		System.out.println(supplier2.get());
	}


	public static void supplierDemo2(){
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
		//返回一个optional对象
		Optional<Integer> first = stream.filter(i -> i > 4)
				.findFirst();

		//optional对象有需要Supplier接口的方法
		//orElse，如果first中存在数，就返回这个数，如果不存在，就放回传入的数
		System.out.println(first.orElse(1));
		System.out.println(first.orElse(7));

		System.out.println("********************");

		Supplier<Integer> supplier = new Supplier<Integer>() {
			@Override
			public Integer get() {
				//返回一个随机值
				return new Random().nextInt();
			}
		};

		//orElseGet，如果first中存在数，就返回这个数，如果不存在，就返回supplier返回的值
		System.out.println(first.orElseGet(supplier));
	}







}
