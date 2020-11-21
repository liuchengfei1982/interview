package com.eagle.interview.IKM;

import java.math.BigDecimal;

public class Q31 implements Q31Interface {
	public Q31(BigDecimal initval){
//		balance = initval; //error
	}
	public String toString(){
		return balance.toString();
	}
	public static void main(String[] args) {
		Q31 test = new Q31(new BigDecimal(50.00));
		System.out.println(test.toString());
	}

	@Override
	public void test() {

	}
}
