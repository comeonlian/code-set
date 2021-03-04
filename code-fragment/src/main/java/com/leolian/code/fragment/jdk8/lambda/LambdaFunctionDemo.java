package com.leolian.code.fragment.jdk8.lambda;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Function;

public class LambdaFunctionDemo {

	public static void main(String[] args) {
		/*Function<String, Integer> func = x -> Integer.parseInt(x);
		Integer apply1 = func.apply("11");
		System.out.println(apply1);*/
		
		Function<Object, Integer> func = x -> {
			System.out.println("apply");
			return 2;
		};
		
		Function.identity().andThen(func).apply(3);
		//System.out.println(apply2);
	}

}