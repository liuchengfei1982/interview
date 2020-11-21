package com.eagle.interview.IKM;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Q53 {
	public static void main(String[] args) {

		/**
		 * public static<T, R> Collector<T, R, R> of(Supplier<R> supplier,
		 *                                               BiConsumer<R, T> accumulator,
		 *                                               BinaryOperator<R> combiner,
		 *                                               Characteristics... characteristics)
		 */
//		Collector<String, ?, TreeSet<String>> myColl = Collector.of(
//				TreeSet::new,
//				(s,t) -> s.add(t.toLowerCase()),
//				(x,y) -> {x.addAll(y); return x;}); //x, y 的类型都是TreeSet
//		TreeSet<String> coll;

//		Collector<String, ?, HashMap<Integer,String>> myColl = Collector.of(
//				HashMap::new,
//				(s,t) -> s.put(t.length(),t.toLowerCase()),
//				(x,y) -> {x.put(y); return x;}); //x, y 的类型都是map
//		HashMap<Integer,String> coll;

//		Collector<String, ?, TreeMap<Integer, String>> myColl = Collector.of(TreeMap::new,
//				(s,t) -> s.put(t.length(),t.toLowerCase()),
//				(x,y) -> {x.putAll(y); return x;});
//		TreeMap<Integer,String> coll;

		Collector<String, ?, AbstractMap<Integer, String>> myColl = Collector.of(TreeMap::new,
				(s,t) -> s.put(t.length(),t.toLowerCase()),
				(x,y) -> {x.putAll(y); return x;});
		AbstractMap<Integer,String> coll;

		coll = Stream.of("Bob", "Carol", "Ted", "Bob", "Alice").collect(myColl);
		coll.forEach((k,v) ->{
			System.out.println(k+":"+v);
		});
	}
}
