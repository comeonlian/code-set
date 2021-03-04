/**
 * 
 */
package com.leolian.code.fragment.jdk8.others;

import java.util.Arrays;

import com.leolian.code.fragment.jdk8.others.annotation.Basic;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月25日 下午2:10:08
 */
@Basic(name = "fix")
@Basic(name = "todo")
public class Person {

	public static void main(String[] args) {
		Basic[] basics = Person.class.getAnnotationsByType(Basic.class);
		Arrays.asList(basics).forEach(a -> {
			System.out.println(a.name());
		});
	}

}
