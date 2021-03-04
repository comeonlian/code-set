package com.leolian.code.fragment.book.concurrence.chapter16;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class ResourceFactory {
	private static class ResourceHolder {
		public static Resource resource = new Resource();
	}

	public static Resource getInstance() {
		return ResourceHolder.resource;
	}

}