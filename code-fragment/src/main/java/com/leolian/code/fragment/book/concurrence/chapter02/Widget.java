package com.leolian.code.fragment.book.concurrence.chapter02;

/**
 * Description: 
 * @author lianliang
 * @date 2018年1月6日 上午10:45:50
 */
public class Widget {
	public synchronized void doSomething() {
		// ...
	}
}

class LoggingWidget extends Widget {
	public synchronized void doSomething() {
		System.out.println(toString()+": calling doSomething");
		// 锁的重入
		super.doSomething();
	}
}