package com.leolian.code.fragment.book.concurrence.chapter11;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.concurrent.GuardedBy;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class BetterAttributeStore {
	
	@GuardedBy("this")
	private final Map<String, String> attributes = new HashMap<>();

	public boolean userLocationMatches(String name, String regexp) {
		String key = "users." + name + ".location";
		String location;
		synchronized (this) {
			location = attributes.get(key);
		}
		if (location == null)
			return false;
		else
			return Pattern.matches(regexp, location);
	}

}