package com.leolian.code.fragment.book.concurrence.chapter02;

import com.leolian.code.fragment.book.concurrence.common.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {
	private LazyInitRace instance = null;

	public LazyInitRace getInstance() {
		if (instance == null)
			instance = new LazyInitRace();
		return instance;
	}
}