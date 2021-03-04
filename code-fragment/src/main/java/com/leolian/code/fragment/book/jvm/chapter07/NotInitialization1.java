package com.leolian.code.fragment.book.jvm.chapter07;

/**
 * 被动使用类字段一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 * @description: 
 * @author lianliang
 * @date 2018年9月12日 上午11:37:06
 */
public class NotInitialization1 {

	public static void main(String[] args) {
		System.out.println(SubClass.value);
	}

}

class SuperClass {

	public static int value = 11;

	static {
		System.out.println("SuperClass init");
	}

}

class SubClass extends SuperClass {

	static {
		System.out.println("SubClass init");
	}

}
