/**
 * 
 */
package com.leolian.code.fragment.book.concurrent.chapter03;

/**
 * Description:
 * @author lianliang
 * @date 2018年1月4日 下午5:35:08
 */
public class VolatileExample {
	int a = 0;
	// boolean flag = false;
	volatile boolean flag = false;

	public void writer() {
		a = 2; // 1
		flag = true; // 2
	}

	public void reader() {
		if (flag) { // 3
			int i = a; // 4
			int temp = i * i;
			System.out.println("temp: "+temp);
		}
	}
	
	public static void main(String[] args) {
		VolatileExample obj = new VolatileExample();
		new Thread(new Runnable() {
			@Override
			public void run() {
				obj.writer();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				obj.reader();
			}
		}).start();
	}
	/*
	 * volatile写-读建立的happens-before关系
	 * 1）根据程序次序规则，1 happens-before 2;3 happens-before 4
	 * 2）根据volatile规则，2 happens-before 3
	 * 3）根据happens-before的传递性规则，1 happens-before 4
	 * */
	
}
