package com.leolian.code.fragment.jdk8.lambda;

public class HelloLambda {
	Runnable r1 = () -> System.out.println(this);
	Runnable r2 = () -> System.out.println(toString());

	/*@Override
	public String toString() {
		return "Hello, lambda!";
	}*/

	public static void main(String[] args) {
		new HelloLambda().r1.run();
		new HelloLambda().r2.run();
	}

}