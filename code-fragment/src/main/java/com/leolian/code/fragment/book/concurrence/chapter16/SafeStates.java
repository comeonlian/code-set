package com.leolian.code.fragment.book.concurrence.chapter16;

import java.util.HashMap;
import java.util.Map;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class SafeStates {
	private final Map<String, String> states;

	public SafeStates() {
		states = new HashMap<String, String>();
		states.put("alaska", "AK");
		states.put("alabama", "AL");
		states.put("wyoming", "WY");
	}

	public String getAbbreviation(String s) {
		return states.get(s);
	}

}