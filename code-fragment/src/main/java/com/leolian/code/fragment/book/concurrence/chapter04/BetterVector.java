package com.leolian.code.fragment.book.concurrence.chapter04;

import java.util.Vector;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

/**
 * Description: 扩展Vector功能
 * @author lianliang
 * @date 2018年1月12日 下午7:49:23
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
	private static final long serialVersionUID = -4501386585084549735L;

	public synchronized boolean putIfAbsent(E x) {
		boolean absent = !contains(x);
		if(absent)
			add(x);
		return absent;
	}
}