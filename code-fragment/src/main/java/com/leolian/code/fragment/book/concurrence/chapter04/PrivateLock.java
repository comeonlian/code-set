package com.leolian.code.fragment.book.concurrence.chapter04;

import javax.annotation.concurrent.GuardedBy;

import com.leolian.code.fragment.book.concurrence.chapter02.Widget;

public class PrivateLock {
	private final Object myLock = new Object();
	@GuardedBy("myLock")
	Widget widget;

	void someMethod() {
		synchronized (myLock) {
			// 访问或修改Widget的状态
		}
	}
}

