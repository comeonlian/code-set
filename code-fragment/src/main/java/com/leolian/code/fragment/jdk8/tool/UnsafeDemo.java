/**
 * 
 */
package com.leolian.code.fragment.jdk8.tool;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月19日 上午9:03:24
 */
public class UnsafeDemo {

	@SuppressWarnings("restriction")
	public static void main(String[] args) throws Exception {
		
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
		System.out.println(unsafe.addressSize());
		
	}

}
