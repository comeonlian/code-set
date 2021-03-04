package com.leolian.javaweb.framework.chapter05;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class JaxWsServer {
	
	public static void main(String[] args) {
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setAddress("http://localhost:8283/ws/soap/hello");
		factory.setServiceClass(HelloService.class);
		factory.setServiceBean(new HelloServiceImpl());
		factory.create();
		System.out.println("soap ws is published");
	}
	
}