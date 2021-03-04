package com.leolian.code.fragment.book.concurrence.chapter16;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class UnsafeLazyInitialization {
	private static Resource resource;

	public static Resource getInstance() {
		if (resource == null)
			resource = new Resource(); // 不安全的发布
		return resource;
	}

}