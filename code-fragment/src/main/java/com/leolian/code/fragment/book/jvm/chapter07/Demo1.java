package com.leolian.code.fragment.book.jvm.chapter07;

public class Demo1 {

	public static void main(String[] args) {
		System.out.println(Sub.B);
	}

	static class Parent {
		public static int A = 1;
		static {
			A = 2;
			// C = 11;
			// System.out.println(C);
		}
		public static int C = 5;
	}

	static class Sub extends Parent {
		public static int B = A;
	}
}