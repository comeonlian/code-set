package com.leolian.code.fragment.book.jvm.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args:
 * -Dfile.encoding=UTF-8 -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=logs/ -XX:SurvivorRatio=8 #存活比例
 */
public class HeapOOM {
	static class OOMObject {

	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();

		while (true) {
			list.add(new OOMObject());
		}
	}

}