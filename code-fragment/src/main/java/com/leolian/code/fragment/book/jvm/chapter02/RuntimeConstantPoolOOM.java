package com.leolian.code.fragment.book.jvm.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -XX:PermSize=10m -XX:MaxPermSize=10m
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}

}