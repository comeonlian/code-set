package com.leolian.code.fragment.book.jvm.chapter08;

/**
 * 动态分派
 * @description: 
 * @author lianliang
 * @date 2018年9月14日 上午11:21:11
 */
public class DynamicDispatch {

	static abstract class Human {
		protected abstract void sayHello();
	}

	static class Man extends Human {
		@Override
		protected void sayHello() {
			System.out.println("man say hello");
		}
	}

	static class Woman extends Human {
		@Override
		protected void sayHello() {
			System.out.println("woman say hello");
		}
	}

	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		man.sayHello();
		woman.sayHello();
		man = new Woman();
		man.sayHello();
	}

}