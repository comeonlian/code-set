/**
 * 
 */
package com.leolian.code.fragment.book.concurrent.chapter03;

/**
 * Description: 做同步
 * @author lianliang
 * @date 2018年1月3日 下午4:59:35
 */
public class SynchronizedExample {
	int a = 0;
	boolean flag = false;

	public synchronized void writer() {
		a = 1; // 1
		flag = true; // 2
	}

	public synchronized void reader() {
		if (flag) { // 3
			int i = a * a; // 4
			// ……
		}
	}
}
