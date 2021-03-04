package com.leolian.code.fragment.book.jvm.chapter08;

/**
 * vm参数 -verbose:gc
 * @description: 
 * @author lianliang
 * @date 2018年9月13日 下午3:57:21
 */
public class Demo1 {

	public static void main(String[] args) {
//		byte[] placeholder = new byte[64 * 1024 * 1024];
//		System.gc();
		
		{
			byte[] placeholder = new byte[64 * 1024 * 1024];
			placeholder = null;
		}
		
//		int a = 0;
		
		System.gc();
		
	}

}
