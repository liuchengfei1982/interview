package com.eagle.interview.IKM;

public class Q38 {
	private int data;
	public Q38(){
		this(10); //调用了下面的构造函数
	}
	public Q38(int data){
		this.data = data;
	}

	public void display(){
		class Decrement {
			public void decrement() {
				data--;
			}
		}
		Decrement d = new Decrement();
		d.decrement();
		System.out.println("data="+data);
	}

	public static void main(String[] args) {
		int data = 0;
		Q38 test =  new Q38();
		test.display();
		System.out.println("data="+data);
	}
}
