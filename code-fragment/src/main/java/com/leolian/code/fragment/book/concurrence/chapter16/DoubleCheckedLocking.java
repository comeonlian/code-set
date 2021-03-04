package com.leolian.code.fragment.book.concurrence.chapter16;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class DoubleCheckedLocking {
	private volatile static Resource resource;

	public static Resource getInstance() {
		if (resource == null) {
			synchronized (DoubleCheckedLocking.class) {
				if (resource == null)
					resource = new Resource();
			}
		}
		return resource;
	}

}