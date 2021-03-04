package com.leolian.code.fragment.book.concurrence.chapter04;

import com.leolian.code.fragment.book.concurrence.common.Immutable;

@Immutable
public class Point {
	public final int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}