/**
 * 
 */
package com.leolian.code.fragment.jdk8.functional;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Description: 内置函数式接口
 * @Author lianliang
 * @Date 2018年4月27日 下午4:16:02
 */
public class FunctionalInterfaceDemo {
	
	public static void main(String[] args) {
		/*
		Predicate<String> predicate = (s) -> s.length() > 0;

		System.out.println(predicate.test("foo"));
		System.out.println(predicate.negate().test("foo"));

		Predicate<Boolean> nonNull = Objects::nonNull;
		Predicate<Boolean> isNull = Objects::isNull;

		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotEmpty = isEmpty.negate();
		*/
		
		/*
		Function<String, Integer> toInt = Integer::valueOf;
		Function<String, String> backToString = toInt.andThen(String::valueOf);

		backToString.apply("12233");
		*/
		
		/*
		Supplier<Person> personSupplier = Person::new;
		Person person = personSupplier.get(); // new Person
		System.out.println(person);
		*/
		
		/*
		Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
		greeter.accept(new Person("Luke", "Skywalker"));
		*/
		
		
		/*
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");

		System.out.println(comparator.compare(p1, p2));
		System.out.println(comparator.reversed().compare(p1, p2));
		*/
		
		/*
		Optional<String> optional = Optional.of("abc");

		optional.isPresent(); // true
		optional.get(); // "bam"
		optional.orElse("fallback"); // "bam"
		
		optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"
		*/
		
		
		
	}
	
}
