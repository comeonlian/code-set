package com.leolian.code.fragment.book.concurrence.chapter14;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
	public BoundedBuffer(int size) {
		super(size);
	}
	
	public synchronized void put(V v) throws Exception {
		while(isFull()) {
			wait();
		}
		doPut(v);
		notifyAll();
	}
	
	public synchronized V take() throws Exception {
		while(isEmpty()) {
			wait();
		}
		V v = doTake();
		notifyAll();
		return v;
	}
	
	
	/*
	public void stateDependentMethod() {
		synchronized (lock) {
			while (!conditionPredicate()) {
				lock.wait();
			}
		}
	}
	*/
	
}