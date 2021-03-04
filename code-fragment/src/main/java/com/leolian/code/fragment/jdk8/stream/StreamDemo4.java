/**
 * 
 */
package com.leolian.code.fragment.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description: 
 * @Author lianliang
 * @Date 2018年4月27日 下午4:47:46
 */
public class StreamDemo4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		
		// Filter接受一个predicate接口类型的变量，并将所有流对象中的元素进行过滤。该操作是一个中间操作，
		// 因此它允许我们在返回结果的基础上再进行其他的流操作（forEach）
		stringCollection
		.stream()
		.filter((s) -> s.startsWith("a"))
		.forEach(System.out::println);
		
		// Sorted是一个中间操作，能够返回一个排过序的流对象的视图。流对象中的元素会默认按照自然顺序进行排序
		stringCollection
		.stream()
		.sorted()
		.filter((s) -> s.startsWith("a"))
		.forEach(System.out::println);
		System.out.println(stringCollection);
		
		// map是一个对于流对象的中间操作，通过给定的方法，它能够把流对象中的每一个元素对应到另外一个对象上。
		// 把每一种对象映射成为其他类型。对于带泛型结果的流对象，具体的类型还要由传递给map的泛型方法来决定
		stringCollection
		.stream()
		.map(String::toUpperCase)
		.sorted((a, b) -> b.compareTo(a))
		.forEach(System.out::println);
		
		// 匹配操作有多种不同的类型，都是用来判断某一种规则是否与流对象相互吻合的。所有的匹配操作都是终结操作，只返回一个boolean类型的结果
		boolean anyStartsWithA = stringCollection
			.stream()
			.anyMatch((s) -> s.startsWith("a"));

		System.out.println(anyStartsWithA); // true

		boolean allStartsWithA = stringCollection
			.stream()
			.allMatch((s) -> s.startsWith("a"));

		System.out.println(allStartsWithA); // false

		boolean noneStartsWithZ = stringCollection
			.stream()
			.noneMatch((s) -> s.startsWith("z"));

		System.out.println(noneStartsWithZ); // true
		
		// Count是一个终结操作，它的作用是返回一个数值，用来标识当前流对象中包含的元素数量
		long startsWithB =stringCollection
				.stream()
				.filter((s) -> s.startsWith("b"))
				.count();

		System.out.println(startsWithB); // 3
		
		// 终结操作，它能够通过某一个方法，对元素进行削减操作。该操作的结果会放在一个Optional变量里返回
		Optional<String> reduced =stringCollection.stream()
				.sorted()
				.reduce((s1, s2) -> s1 + "#" + s2);
		
		reduced.ifPresent(System.out::println);
		// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
		
	}

}
