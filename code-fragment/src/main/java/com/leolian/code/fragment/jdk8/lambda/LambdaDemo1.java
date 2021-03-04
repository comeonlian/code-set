/**
 * 
 */
package com.leolian.code.fragment.jdk8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * Description: 
 * @author lianliang
 * @date 2018年4月13日 下午5:05:22
 */
public class LambdaDemo1 {
	
	public static void main(String[] args) throws Exception {
		/*Runnable run = () -> {
			System.out.println("lambda");
			System.out.println("runnable");
		};
		new Thread(run).start();*/
		
//		BinaryOperator<Integer> sum = (x, y) -> x+y;
//		Integer res = sum.apply(15, 16);
//		System.out.println(res);
		
		
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, (a, b) -> b.compareTo(a));
		names.forEach(System.out::println);
		
		
		
	}
	
}
