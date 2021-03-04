package com.leolian.code.fragment.book.jvm.chapter08;

import java.io.Serializable;

/**
 * 静态分派
 * @description: 
 * @author lianliang
 * @date 2018年9月14日 上午11:13:33
 */
public class Overload {

	/*public static void sayHello(Object arg) {
		System.out.println("hello Object");
	}*/

	/*public static void sayHello(int arg) {
		System.out.println("hello int");
	}*/

	/*public static void sayHello(long arg) {
		System.out.println("hello long");
	}*/

	/*public static void sayHello(Character arg) {
		System.out.println("hello Character");
	}*/

	/*public static void sayHello(char arg) {
		System.out.println("hello char");
	}*/
	
	public static void sayHello(char... arg) {
		System.out.println("hello char ...");
	}
	
	/*public static void sayHello(Serializable arg) {
		System.out.println("hello Serializable");
	}*/

	public static void main(String[] args) {
		sayHello('a');
	}

}