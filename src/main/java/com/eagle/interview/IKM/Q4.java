package com.eagle.interview.IKM;

import java.util.Optional;

public class Q4 {
	public static void main(String[] args) {
		String s = null;
		Optional<String> b = Optional.empty();
		try{
			System.out.println(s.length());
			System.out.println(b.orElse("").length());
		}catch (Exception ex){
			System.out.println(s);
		}finally {
			s = "String";
			System.out.println(s.length());
			b= Optional.ofNullable("");
			System.out.println(b.get().length());
		}

	}
}
