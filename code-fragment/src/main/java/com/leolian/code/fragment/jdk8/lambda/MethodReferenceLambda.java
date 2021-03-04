/**
 * 
 */
package com.leolian.code.fragment.jdk8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月13日 下午4:18:35
 */
public class MethodReferenceLambda {

	public static void main(String[] args) {
		List<User> users = Arrays.asList(new User("lily"), new User("jack"), new User("bob"), new User("cacy"));
		
		// lambda 方式
		// Comparator<User> comparator = Comparator.comparing(u -> u.getUserName());
		
		/*
		传统方式
		Comparator<User> comparator = new Comparator<User>() {
			@Override
			public int compare(User u1, User u2) {
				return u1.getUserName().compareTo(u2.getUserName());
			}
		};
		*/
		
		// 方法引用方式
		Comparator<User> comparator = Comparator.comparing(User::getUserName);
		
		Collections.sort(users, comparator);
		users.forEach(user -> {System.out.println(user.getUserName());});
	}

}

class User {

	private String userName;
	
	/**
	 * @param userName
	 */
	public User(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

}
