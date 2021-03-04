package com.leolian.code.fragment.jdk8.lambda;

public class FunctionInterfaceDemo {

	@FunctionalInterface
	interface Predicate<T> {
		boolean test(T t);
	}

	public static boolean doPredicate(int age, Predicate<Integer> predicate) {
		return predicate.test(age);
	}

	public static void main(String[] args) {
		boolean isAdult = doPredicate(15, age -> age >= 18);
		System.out.println(isAdult);
	}
}