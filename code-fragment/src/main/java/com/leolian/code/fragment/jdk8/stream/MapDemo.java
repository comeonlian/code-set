/**
 * 
 */
package com.leolian.code.fragment.jdk8.stream;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author lianliang
 * @Date 2018年4月27日 下午5:08:48
 */
public class MapDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "hehe" + i + ";");
		}
		map.forEach((id, val) -> System.out.println(val));
		
		System.out.println("------------------------------");
		
		map.computeIfPresent(3, (num, val) -> val + num);
		System.out.println(map.get(3)); // val33

		map.computeIfPresent(4, (num, val) -> null);
		System.out.println(map.containsKey(9)); // false

		map.computeIfAbsent(23, num -> "val" + num);
		System.out.println(map.containsKey(23)); // true

		map.computeIfAbsent(21, num -> "bam");
		System.out.println(map.get(21)); // val33
		
		System.out.println("------------------------------");
		
		map.forEach((id, val) -> System.out.println(val));
		
		
		System.out.println("------------------------------");
		
		System.out.println(map.getOrDefault(42, "not found")); // not found
		
		map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
		System.out.println(map.get(9)); // val9

		map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
		System.out.println(map.get(9)); // val9concat
		
		
	}

}
