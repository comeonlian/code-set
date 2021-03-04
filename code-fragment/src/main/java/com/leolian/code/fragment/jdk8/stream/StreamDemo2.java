/**
 * 
 */
package com.leolian.code.fragment.jdk8.stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月19日 上午9:00:35
 */
public class StreamDemo2 {

	public static void main(String[] args) throws Exception {

		String content = Files.readAllLines(Paths.get("D:\\Temp\\Demo\\abc.txt"))
				.stream()
				.collect(Collectors.joining("\n"));
		System.out.println(content);

	}

}
