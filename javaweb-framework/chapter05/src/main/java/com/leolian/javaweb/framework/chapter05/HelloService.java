package com.leolian.javaweb.framework.chapter05;

import javax.jws.WebService;

@WebService
public interface HelloService {

    String say(String name);

}