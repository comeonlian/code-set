package com.leolian.code.fragment.book.concurrence.chapter03;

import com.leolian.code.fragment.book.concurrence.common.NotThreadSafe;

@NotThreadSafe
public class MutableInteger {
	private int value;

	public int get() {
		return value;
	}

	public void set(int value) {
		this.value = value;
	}
}