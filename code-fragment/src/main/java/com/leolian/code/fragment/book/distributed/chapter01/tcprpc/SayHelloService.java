package com.leolian.code.fragment.book.distributed.chapter01.tcprpc;

public interface SayHelloService {
	/**
	 * 问好方法
	 * @param helloArg
	 * @return
	 */
	String sayHello(String helloArg);
}