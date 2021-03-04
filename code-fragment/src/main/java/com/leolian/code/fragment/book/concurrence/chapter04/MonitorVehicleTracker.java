package com.leolian.code.fragment.book.concurrence.chapter04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.GuardedBy;

import com.leolian.code.fragment.book.concurrence.common.NotThreadSafe;
import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class MonitorVehicleTracker {
	@GuardedBy("this")
	private final Map<String, MutablePoint> locations;

	public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
		this.locations = deepCopy(locations);
	}

	public synchronized Map<String, MutablePoint> getLOcations() {
		return deepCopy(locations);
	}

	public synchronized void setLocation(String id, int x, int y) {
		MutablePoint loc = locations.get(id);
		if (loc == null)
			throw new IllegalArgumentException("No such ID: " + id);
		loc.x = x;
		loc.y = y;
	}

	private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
		Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();
		for (String id : m.keySet()) {
			result.put(id, new MutablePoint(m.get(id)));
		}
		return Collections.unmodifiableMap(result);
	}
}

@NotThreadSafe
class MutablePoint {
	public int x, y;
	
	public MutablePoint() {
		x = 0;
		y = 0;
	}
	
	public MutablePoint(MutablePoint p) {
		this.x = p.x;
		this.y = p.y;
	}
}