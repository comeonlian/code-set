package com.leolian.code.fragment.book.concurrence.chapter03;

import javax.annotation.concurrent.GuardedBy;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class SynchronizedInteger {
	@GuardedBy("this")
	private int value;

	public synchronized int get() {
		return value;
	}

	public synchronized void set(int value) {
		this.value = value;
	}
}