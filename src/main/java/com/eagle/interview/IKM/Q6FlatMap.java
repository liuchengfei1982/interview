package com.eagle.interview.IKM;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Q6FlatMap {
	static class Indicate{
		public String s;
		public List<Integer> list;
		public Indicate(String s){
			this.s = s;
			list = new LinkedList<>();
			for (int i = 0; i < s.length(); i++) {
				list.add(i);
			}
		}
	}
	public static void main(String[] args) {

		/**
		 * 传递给map方法的lambda为每个单词生成了一个String[](String列表)。
		 * 因此，map返回的流实际上是Stream<String[]> 类型的。
		 */
		String[] words = new String[]{"Hello","World"};
		List<String[]> a = Arrays.stream(words)
				.map(word -> word.split(""))
				.distinct() //去重
				.collect(toList());
		a.forEach(System.out::print);

		/**
		 * 使用flatMap方法的效果是，各个数组并不是分别映射一个流，
		 * 而是映射成流的内容，
		 * 所有使用map(Array::stream)时生成的单个流被合并起来，即扁平化为一个流
		 */
		List<String> b = Arrays.stream(words)
				.map(word -> word.split(""))
				.flatMap(Arrays::stream)
				.distinct()  //去重
				.collect(toList());
		b.forEach(System.out::print);

		Stream.of(new Indicate("Mercury"),
				new Indicate("Venus"),
				new Indicate("Earth"))
				.flatMap(s -> s.list.stream()) //流化每个Indicate对象中的List<Integer>，并操作List里面的元素
				.mapToInt(j ->j) //为下一步max提供条件
				.max() //这里可以重载Comparator.comparing()来实现比较规则
				.ifPresent(s -> System.out.println(s)); //如果有则输出


	}
}
