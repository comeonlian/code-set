package com.leolian.distributed.architecture.chapter01.rmi;

import java.rmi.Remote;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/12 16:30
 */
public interface HelloService extends Remote {

    String sayHello(String someOne) throws Exception;

}
