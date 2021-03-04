package com.leolian.code.fragment.book.concurrence.chapter15;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class CasCounter {
	private SimulatedCAS value;

	public int getValue() {
		return value.get();
	}

	public int increament() {
		int v;
		do {
			v = value.get();
		} while (v != value.compareAndSwap(v, v + 1));
		return v + 1;
	}

}