/**
 * 
 */
package com.leolian.code.fragment.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author lianliang
 * @Date 2018年4月27日 下午5:04:44
 */
public class ParallelStreamDemo {

	public static void main(String[] args) {
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
		
		// sequential sort took: 617 ms
		long t0 = System.nanoTime();

		long count = values.stream().sorted().count();
		System.out.println(count);

		long t1 = System.nanoTime();

		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));
		
		
		// parallel sort took: 361 ms
		long t2 = System.nanoTime();

		long count1 = values.parallelStream().sorted().count();
		System.out.println(count1);

		long t3 = System.nanoTime();

		long millis1 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
		System.out.println(String.format("parallel sort took: %d ms", millis1));
		
	}

}
