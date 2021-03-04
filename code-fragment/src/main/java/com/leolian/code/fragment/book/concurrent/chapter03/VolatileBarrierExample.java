/**
 * 
 */
package com.leolian.code.fragment.book.concurrent.chapter03;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年1月4日 下午8:00:33
 */
public class VolatileBarrierExample {
	int a;
	volatile int v1 = 1;
	volatile int v2 = 2;

	void readAndWrite() {
		int i = v1; // 第一个volatile读
		int j = v2; // 第二个volatile读
		a = i + j; // 普通写
		v1 = i + 1; // 第一个volatile写
		v2 = j * 2; // 第二个 volatile写
	} 
	
	// 其他方法
	// ...
}
