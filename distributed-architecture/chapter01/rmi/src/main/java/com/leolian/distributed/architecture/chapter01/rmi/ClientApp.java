package com.leolian.distributed.architecture.chapter01.rmi;

import java.rmi.Naming;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/12 16:44
 */
public class ClientApp {
    public static final int SERVICE_PORT = 8801;

    public static void main(String[] args) throws Exception {
        //服务引入
        HelloService helloService = (HelloService) Naming.lookup("rmi://localhost:" + SERVICE_PORT + "/helloService");
        //调用远程方法
        System.out.println("RMI服务器返回的结果是: " + helloService.sayHello("Jack"));
    }

}
