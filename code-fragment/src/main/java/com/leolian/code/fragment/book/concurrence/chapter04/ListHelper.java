package com.leolian.code.fragment.book.concurrence.chapter04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class ListHelper<E> {
	public List<E> list = Collections.synchronizedList(new ArrayList<E>());

	public boolean putIfAbsent(E x) {
		synchronized (list) {
			boolean absent = !list.contains(x);
			if (absent)
				list.add(x);
			return absent;
		}
	}

}