package com.eagle.interview;

public class main {
	public static void main(String[] args) {
		String resp = "{\n"+
				"\"code\": \"0001\",\n" +
				"\"message\": \"服务内部异常\",\n" +
				"\"detail\": {\n \"message\": \"系统异常，请稍后重试\"\n"+
				"},\n"+"\"result\":null\n"+
				"}";
		System.out.println(resp);
	}
}
