package com.leolian.code.fragment.book.concurrence.chapter14;

import com.leolian.code.fragment.book.concurrence.chapter14.exception.BufferEmptyException;
import com.leolian.code.fragment.book.concurrence.chapter14.exception.BufferFullException;
import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
	public GrumpyBoundedBuffer(int size) {
		super(size);
	}

	public synchronized void put(V v) throws BufferFullException {
		if (isFull()) {
			throw new BufferFullException();
		}
		doPut(v);
	}

	public synchronized V take() throws BufferEmptyException {
		if (isEmpty()) {
			throw new BufferEmptyException();
		}
		return doTake();
	}
	
	public static void main(String[] args) {
		int SLEEP_GRANULARITY = 1000;
		GrumpyBoundedBuffer<String> buffer = new GrumpyBoundedBuffer<String>(1024);
		while(true) {
			try {
				String item = buffer.take();
				break;
			} catch (BufferEmptyException e) {
				try {
					Thread.sleep(SLEEP_GRANULARITY);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
}