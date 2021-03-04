package com.leolian.code.fragment.book.concurrence.chapter15;

import javax.annotation.concurrent.GuardedBy;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class SimulatedCAS {
	@GuardedBy("this")
	private int value;

	public synchronized int get() {
		return value;
	}

	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;
		if (oldValue == expectedValue) {
			value = newValue;
		}
		return oldValue;
	}

	public synchronized boolean compareAndSet(int expectedValue, int newValue) {
		return (expectedValue == compareAndSwap(expectedValue, newValue));
	}

}