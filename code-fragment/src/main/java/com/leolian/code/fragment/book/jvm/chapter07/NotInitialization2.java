package com.leolian.code.fragment.book.jvm.chapter07;

/**
 * 被动使用类字段演示二: 
 * 通过数组定义来引用类，不会触发此类的初始化
 * @description: 
 * @author lianliang
 * @date 2018年9月12日 上午11:40:45
 */
public class NotInitialization2 {

	public static void main(String[] args) {
		SuperClass[] sca = new SuperClass[10];
	}

}