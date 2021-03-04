package com.leolian.code.fragment.book.jvm.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: -Xms100m -Xmx100m -XX:+UseSerialGC
 * @author lianliang
 * @date 2018年9月6日 上午11:00:06
 */
public class JConsoleDemo01 {

	static class OOMObject {
		public byte[] placeholder = new byte[64 * 1024];
	}

	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Thread.sleep(100);
			list.add(new OOMObject());
		}
		System.gc();
	}

	public static void main(String[] args) throws InterruptedException {
		fillHeap(1000);
	}

}
