package com.leolian.javaweb.framework.chapter07;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/7 10:20
 */
@WebService
@Component(value = "helloServiceImpl")
public class HelloServiceImpl implements HelloService {

    @Override
    public String say(String name) {
        return "Hello, " + name + " !";
    }

}
