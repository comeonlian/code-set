package com.leolian.springboot.demo3;

import com.leolian.springboot.demo3.classloader.MsgHandler;

/**
 * Unit test for simple App.
 */
public class ClassLoaderTest {
	
	public static void main(String[] args) {
		new Thread(new MsgHandler()).start();
	}
	
}
