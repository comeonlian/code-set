package com.leolian.code.fragment.book.concurrence.chapter03;

import java.util.HashSet;
import java.util.Set;

import com.leolian.code.fragment.book.concurrence.common.Immutable;

@Immutable
public final class ThreeStooges {
	private final Set<String> stooges = new HashSet<String>();

	public ThreeStooges() {
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
	}

	public boolean isStooge(String name) {
		return stooges.contains(name);
	}
}