package com.leolian.code.fragment.book.concurrence.chapter15;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class ReentrantLockPseudoRandom extends PseudoRandom {
	private final Lock lock = new ReentrantLock(false);
	private int seed;

	public ReentrantLockPseudoRandom(int seed) {
		this.seed = seed;
	}

	public int nextInt(int n) {
		lock.lock();
		try {
			int s = seed;
			seed = calculateNext(s);
			int remainder = s % n;
			return remainder > 0 ? remainder : remainder + n;
		} finally {
			lock.unlock();
		}
	}
	

}