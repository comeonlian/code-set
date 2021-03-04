package com.leolian.code.fragment.book.distributed.chapter01.tcprpc;

public class SayHelloServiceImpl implements SayHelloService {
	
	@Override
	public String sayHello(String helloArg) {
		if(helloArg.equalsIgnoreCase("hello")) {
			return "hello";
		} else {
			return "bye bye";
		}
	}
	
}