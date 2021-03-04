package com.leolian.distributed.architecture.chapter01.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/12 16:32
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    private static final long serialVersionUID = -9025712813079433294L;

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String someOne) throws Exception {
        return "Hello, " + someOne + "!";
    }

}
