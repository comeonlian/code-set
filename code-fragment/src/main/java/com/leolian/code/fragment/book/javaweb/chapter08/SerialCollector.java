package com.leolian.code.fragment.book.javaweb.chapter08;

/**
 * @Description: 应用不同的GC算法触发minorGC和FullGC
 * @author lianliang
 * @date 2017年7月3日 下午4:54:28
 */
public class SerialCollector {
	public static void main(String[] args){
		int m = 1024 * 1024;
		byte[] b = new byte[2*m];
		byte[] b2 = new byte[2*m];
		byte[] b3 = new byte[2*m];
		byte[] b4 = new byte[2*m];
		byte[] b5 = new byte[2*m];
		byte[] b6 = new byte[2*m];
		byte[] b7 = new byte[2*m];
		System.out.println("Test SerialCollector");
	}
}