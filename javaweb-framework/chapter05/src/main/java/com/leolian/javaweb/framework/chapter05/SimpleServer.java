package com.leolian.javaweb.framework.chapter05;

import org.apache.cxf.frontend.ServerFactoryBean;

public class SimpleServer {
	
	public static void main(String[] args) {
		ServerFactoryBean factory = new ServerFactoryBean();
		factory.setAddress("http://localhost:8283/ws/soap/hello");
		factory.setServiceClass(HelloService.class);
		factory.setServiceBean(new HelloServiceImpl());
		factory.create();
		System.out.println("soap ws is published");
	}
	
}