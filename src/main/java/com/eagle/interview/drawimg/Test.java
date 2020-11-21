package com.eagle.interview.drawimg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		List<String> orgIdList = new ArrayList<>();
		orgIdList.add("1");
		orgIdList.add("2");
		orgIdList.add("3");
		orgIdList.add("4");
		orgIdList.add("5");

		String str1 = orgIdList.stream().map(i -> i.toString())
				.collect(Collectors.joining(","));



		System.out.println(str1);

	}
}
