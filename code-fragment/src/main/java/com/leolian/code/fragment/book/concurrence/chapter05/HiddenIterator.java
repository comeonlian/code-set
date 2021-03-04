package com.leolian.code.fragment.book.concurrence.chapter05;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.annotation.concurrent.GuardedBy;

/**
 * Description: 存在并发修改异常
 * @author lianliang
 * @date 2018年1月13日 下午10:42:27
 */
public class HiddenIterator {
	@GuardedBy("this")
	private final Set<Integer> set = new HashSet<Integer>();

	public synchronized void add(Integer i) {
		set.add(i);
	}

	public synchronized void remove(Integer i) {
		set.remove(i);
	}

	public void addTenThings() {
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			add(r.nextInt());
		}
		System.out.println("DEBUG: added ten elemetns to " + set);
	}

}