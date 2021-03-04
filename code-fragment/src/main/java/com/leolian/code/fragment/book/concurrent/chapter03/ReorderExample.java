/**
 * 
 */
package com.leolian.code.fragment.book.concurrent.chapter03;

/**
 * Description: 指令重排序多多线程的影响
 * @author lianliang
 * @date 2018年1月3日 下午4:39:13
 */
public class ReorderExample {
	int a = 0;
	boolean flag = false;

	public void writer() {
		a = 1; // 1
		flag = true; // 2
	}

	public void reader() {
		if (flag) { // 3
			int i = a * a; // 4
			// ……
		}
	}
	
	/*
	 * 1、操作1和操作2做了重排序。程序执行时，线程A首先写标记变量flag，随后线程B读这个变量。
	 * 由于条件判断为真，线程B将读取变量a。此时，变量a还没有被线程A写入，在这里多线程程序的语义被重排序破坏了！
	 * 
	 * 
	 * 2、在程序中，操作3和操作4存在控制依赖关系。当代码中存在控制依赖性时，会影响指令序列执行的并行度。
	 * 为此，编译器和处理器会采用猜测（Speculation）执行来克服控制相关性对并行度的影响。以处理器的猜测执行为例，
	 * 执行线程B的处理器可以提前读取并计算a*a，然后把计算结果临时保存到一个名为重排序缓冲（Reorder Buffer，ROB）的硬件缓存中。
	 * 当操作3的条件判断为真时，就把该计算结果写入变量i中
	 * */
}
