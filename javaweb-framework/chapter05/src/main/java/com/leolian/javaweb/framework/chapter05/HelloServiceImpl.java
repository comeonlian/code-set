package com.leolian.javaweb.framework.chapter05;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/7 10:20
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String say(String name) {
        return "Hello, " + name + " !";
    }

}
