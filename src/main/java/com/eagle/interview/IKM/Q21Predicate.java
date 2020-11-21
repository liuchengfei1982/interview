package com.eagle.interview.IKM;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Q21Predicate {
	public static void main(String[] args) {
		predicateDemo();
	}

	/**
	 * ① Predicate 是一个谓词型接口，其实只是起到一个判断作用。
	 * ② Predicate 通过实现一个 test 方法做判断。
	 *
	 * Predicate还额外提供了另外三个默认方法和一个静态方法
	 * and方法接收一个Predicate类型，也就是将传入的条件和当前条件以并且的关系过滤数据
	 * or方法同样接收一个Predicate类型，将传入的条件和当前的条件以或者的关系过滤数据
	 * negate就是将当前条件取反
	 */
	public static void predicateDemo(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		//输出大于5的数字
		List<Integer> result = conditionFilter(list, integer -> integer > 5);
		result.forEach(System.out::println);
		System.out.println("-------");

		//输出大于等于5的数字
		result = conditionFilter(list, integer -> integer >= 5);
		result.forEach(System.out::println);
		System.out.println("-------");
		//输出小于8的数字
		result = conditionFilter(list, integer -> integer < 8);
		result.forEach(System.out::println);
		System.out.println("-------");
		//输出所有数字
		result = conditionFilter(list, integer -> true);
		result.forEach(System.out::println);
		System.out.println("-------");

		//大于5并且是偶数
		result = conditionFilterAnd(list, i -> i > 5, i -> i % 2 == 0);
		result.forEach(System.out::println);//6 8 10
		System.out.println("-------");

		//大于5或者是偶数
		result = conditionFilterOr(list, i -> i > 5, i -> i % 2 == 0);
		result.forEach(System.out::println);//2 4 6 8 9 10
		System.out.println("-------");

		//条件取反
		result = conditionFilterNegate(list,i -> i > 5);
		result.forEach(System.out::println);// 1 2 3 4 5
		System.out.println("-------");

		//isEqual是静态方法
		System.out.println(Predicate.isEqual("test").test("test"));//true

	}
	//高度抽象的方法定义，复用性高
	public static List<Integer> conditionFilter(List<Integer> list, Predicate<Integer> predicate){
		return list.stream().filter(predicate).collect(Collectors.toList());
	}

	public static List<Integer> conditionFilterNegate(List<Integer> list, Predicate<Integer> predicate){
		return list.stream().filter(predicate.negate()).collect(Collectors.toList());
	}

	public static List<Integer> conditionFilterAnd(List<Integer> list, Predicate<Integer> predicate,Predicate<Integer> predicate2){
		return list.stream().filter(predicate.and(predicate2)).collect(Collectors.toList());
	}

	public static List<Integer> conditionFilterOr(List<Integer> list,
												  Predicate<Integer> predicate,
												  Predicate<Integer> predicate2){
		return list.stream().filter(predicate.or(predicate2)).collect(Collectors.toList());
	}
}
