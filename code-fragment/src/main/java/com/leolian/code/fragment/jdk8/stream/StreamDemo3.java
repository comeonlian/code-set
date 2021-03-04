/**
 * 
 */
package com.leolian.code.fragment.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Description: 
 * @author lianliang
 * @date 2018年4月24日 上午11:25:36
 */
public class StreamDemo3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<List<String>> lists = new ArrayList<>();
		lists.add(Arrays.asList("apple", "click"));
		lists.add(Arrays.asList("boss", "dig", "qq", "vivo"));
		lists.add(Arrays.asList("c#", "biezhi"));

		long count = lists.stream()
			.flatMap(Collection::stream)
			.filter(str -> str.length()==2)
			.count();
		System.out.println(count);
	}

}
