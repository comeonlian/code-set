package com.leolian.code.fragment.book.jvm.chapter08;

/**
 * 方法静态分派
 * @description: 
 * @author lianliang
 * @date 2018年9月14日 上午10:27:24
 */
public class StaticDispatch {

	static abstract class Human {
	}

	static class Man extends Human {
	}

	static class WoMan extends Human {
	}

	public void sayHello(Human guy) {
		System.out.println("hello, guy!");
	}

	public void sayHello(Man guy) {
		System.out.println("hello, gentleman!");
	}

	public void sayHello(WoMan guy) {
		System.out.println("hello, lady!");
	}

	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new WoMan();
		StaticDispatch sd = new StaticDispatch();
		sd.sayHello(man);
		sd.sayHello(woman);
	}

}