package com.leolian.code.fragment.book.concurrence.chapter16;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class SafeLazyInitialization {
	private static Resource resource;

	public synchronized static Resource getInstance() {
		if (resource == null)
			resource = new Resource();
		return resource;
	}

}