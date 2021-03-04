package com.leolian.distributed.architecture.chapter01.cxf.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liyebing created on 16/12/4.
 * @version $Id$
 */
public class CXFClient {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:cxf-client.xml");
        HelloService client = (HelloService) context.getBean("helloClient");
        System.out.println(client.sayHello("Rose"));
    }

}
