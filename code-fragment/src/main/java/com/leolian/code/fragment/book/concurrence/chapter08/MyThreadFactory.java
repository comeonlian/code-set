package com.leolian.code.fragment.book.concurrence.chapter08;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义的线程工厂
 * Description: 
 * @author lianliang
 * @date 2018年2月7日 下午5:43:06
 */
public class MyThreadFactory implements ThreadFactory {
	private final String poolName;

	public MyThreadFactory(String poolName) {
		this.poolName = poolName;
	}

	public Thread newThread(Runnable runnable) {
		// 包装一个线程
		return new MyAppThread(runnable, poolName);
	}
}