package com.leolian.distributed.architecture.chapter01.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/12 16:40
 */
public class ServiceApp {
    public static final int SERVICE_PORT = 8801;

    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(SERVICE_PORT);
        
        HelloService helloService = new HelloServiceImpl();
        Naming.bind("rmi://0.0.0.0:" + SERVICE_PORT + "/helloService", helloService);
        System.out.println("ServiceApp provide RPC service now.");
    }
}
