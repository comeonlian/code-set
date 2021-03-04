package com.leolian.distributed.architecture.chapter01.axis2;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/12 16:32
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String content) {
        return "Hello, " + content + " !";
    }

}
