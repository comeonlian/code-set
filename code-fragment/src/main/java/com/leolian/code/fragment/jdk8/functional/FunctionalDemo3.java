/**
 * 
 */
package com.leolian.code.fragment.jdk8.functional;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月26日 上午9:26:45
 */
public class FunctionalDemo3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		System.out.println(person);
	}

}

class Person {
	String firstName;
	String lastName;

	Person() {
	}

	Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}

@FunctionalInterface
interface PersonFactory<P extends Person> {
	P create(String firstName, String lastName);
}

class Lambda4 {
	static int outerStaticNum;
	int outerNum;

	void testScopes() {
		Converter<Integer, String> stringConverter1 = (from) -> {
			outerNum = 23;
			return String.valueOf(from);
		};

		Converter<Integer, String> stringConverter2 = (from) -> {
			outerStaticNum = 72;
			return String.valueOf(from);
		};
	}
}