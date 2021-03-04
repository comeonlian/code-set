package com.leolian.code.fragment.book.concurrence.chapter14;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
	
	private static final int SLEEP_GRANULARITY = 1000;
	
	public SleepyBoundedBuffer(int size) {
		super(size);
	}

	public void put(V v) throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!isFull()) {
					doPut(v);
					return;
				}
			}
			Thread.sleep(SLEEP_GRANULARITY);
		}
	}

	public V take() throws InterruptedException {
		while (true) {
			synchronized (this) {
				if (!isEmpty())
					return doTake();
			}
			Thread.sleep(SLEEP_GRANULARITY);
		}
	}

}