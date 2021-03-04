/**
 * 
 */
package com.leolian.code.fragment.jdk8.others;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月25日 下午2:06:11
 */
public class CollectDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> persons = Collections.emptyList();

		Stream.of("a", "c", null, "d")
			.filter(Objects::nonNull)
			.forEach(System.out::println);
		
		User user = new User();
		Optional<User> emptyUser = Optional.empty();
		Optional<User> userOptional = Optional.of(user);
		Optional<User> ofNullOptional = Optional.ofNullable(user);
		
		Optional<User> ofNullOptional1 = Optional.ofNullable(user);
		Optional<String> userName = ofNullOptional1.map(User::getName);
		
	}
	
	public static String getUserNameByOptional(User user) {
		Optional<String> userName = Optional.ofNullable(user).map(User::getName);
		return userName.orElse(null);
	}
	
	
	public static String getUserName(User user) {
		return user.getName();
	}
	
}

class User {
	String name;

	public String getName() {
		return name;
	}
}
