package com.leolian.code.fragment.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LambdaSupplierDemo {
	
	public static List<Integer> supply(Integer num, Supplier<Integer> supplier){
		List<Integer> resultList = new ArrayList<>();
		for(int x=0; x<num; x++) {
			resultList.add(supplier.get());
		}
		return resultList;
	}
	
	public static void main(String[] args) {
		Supplier<Integer> supplier = () -> (int)(Math.random()*100);
		/*List<Integer> list = supply(10, supplier);
		list.forEach(System.out::println);*/
		for(int i=0; i<10; i++) {
			System.out.println(supplier.get());
		}
	}
	
}