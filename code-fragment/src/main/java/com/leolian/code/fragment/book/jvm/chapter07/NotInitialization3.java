package com.leolian.code.fragment.book.jvm.chapter07;

/**
 * 被动使用类字段演示三: 
 * 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
 * @description: 
 * @author lianliang
 * @date 2018年9月12日 上午11:46:43
 */
public class NotInitialization3 {

	public static void main(String[] args) {
		System.out.println(ConstClass.HELLOWORLD);
	}

}

class ConstClass {

	public static final String HELLOWORLD = "hello world";

	static {
		System.out.println("ConstClass init");
	}

}