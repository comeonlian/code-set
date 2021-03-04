/**
 * 
 */
package com.leolian.code.fragment.jdk8.functional;

/**
 * 现代化 Java - Java8 指南 Lambda的范围 Description:
 * 
 * @author lianliang
 * @date 2018年4月25日 下午2:34:46
 */
public class FunctionalDemo2 {

	public static void main(String[] args) {
		// Converter<String, Integer> convert = (from) -> Integer.valueOf(from);
		// Integer converted = convert.convert("123");
		// System.out.println(converted);
		//
		// Converter<String, Integer> converter = Integer::valueOf;
		// converted = converter.convert("123");
		// System.out.println(converted);

		Something something = new Something();
		Converter<String, String> converter = something::startWith;
		String converted = converter.convert("Shit");
		System.out.println(converted); // "J"
		
		
		
	}

}

@FunctionalInterface
interface Converter<F, T> {

	T convert(F from);

}

class Something {

	String startWith(String s) {
		return String.valueOf(s.charAt(0));
	}

}
