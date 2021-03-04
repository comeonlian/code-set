package com.leolian.code.fragment.book.concurrence.chapter04;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;

@ThreadSafe
public class ImprovedList<T> implements List<T> {
	private final List<T> list;

	public ImprovedList(List<T> list) {
		this.list = list;
	}

	public synchronized boolean putIfAbsent(T x) {
		boolean contains = list.contains(x);
		if (contains)
			list.add(x);
		return contains;
	}

	public synchronized void clear() {
		list.clear();
	}

	/* (non-Javadoc)
	 * @see java.util.List#size()
	 */
	@Override
	public int size() {
		//  Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		//  Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		//  Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		//  Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		//  Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		//  Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		//  Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		//  Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		//  Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends T> c) {
		//  Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		//  Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		//  Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		//  Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	@Override
	public T get(int index) {
		//  Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public T set(int index, T element) {
		//  Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, T element) {
		//  Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	@Override
	public T remove(int index) {
		//  Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		//  Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		//  Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<T> listIterator() {
		//  Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<T> listIterator(int index) {
		//  Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		//  Auto-generated method stub
		return null;
	}

}