package com.leolian.code.fragment.book.jvm.chapter02;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * VM args: -Xmx10m -XX:MaxDirectMemorySize=5m
 */
@SuppressWarnings("restriction")
public class DirectMemoryOOM {

	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) throws Exception {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		while (true) {
			unsafe.allocateMemory(_1MB);
		}
	}

}