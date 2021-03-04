package com.leolian.code.fragment.book.concurrence.chapter05;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Description: Semaphore实现一个有边界的Set
 * @author lianliang
 * @date 2018年1月15日 上午10:28:16
 */
public class BoundedHashSet<T> {
	private final Set<T> set;
	private final Semaphore sem;
	
	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		sem = new Semaphore(bound);
	}
	
	public boolean add(T o) throws InterruptedException {
		sem.acquire();
		boolean wasAdded = false;
		try {
			wasAdded = set.add(o);
			return wasAdded;
		} finally {
			if(!wasAdded)
				sem.release();
		}
	}
	
	public boolean remove(Object o) {
		boolean wasRemoved = set.remove(o);
		if(wasRemoved)
			sem.release();
		return wasRemoved;
	}
}