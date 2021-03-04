package com.leolian.code.fragment.book.jvm.chapter11;

public class Demo1 {

	public static final int NUM = 15000;

	public static int doubleValue(int i) {
		return i * 2;
	}

	public static long calcSum() {
		long sum = 0;
		for (int i = 0; i <= 100; i++) {
			sum += doubleValue(i);
		}
		return sum;
	}

	public static void main(String[] args) {
		for (int i = 0; i < NUM; i++) {
			calcSum();
		}
	}
}