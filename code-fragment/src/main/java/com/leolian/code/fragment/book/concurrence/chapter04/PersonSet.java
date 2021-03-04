package com.leolian.code.fragment.book.concurrence.chapter04;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.concurrent.GuardedBy;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;
import com.leolian.code.fragment.book.distributed.chapter01.demo.Person;

@ThreadSafe
public class PersonSet {
	@GuardedBy("this")
	private final Set<Person> mySet = new HashSet<Person>();

	public synchronized void addPerson(Person p) {
		mySet.add(p);
	}

	public synchronized boolean containsPerson(Person p) {
		return mySet.contains(p);
	}
}