package com.leolian.distributed.architecture.chapter01.thrift02;

/**
 * @description:
 * @author lianliang
 * @date 2019/1/28 15:50
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(User user) {
        return "Hehe, Hello, " + user.getName() + " !";
    }

}
