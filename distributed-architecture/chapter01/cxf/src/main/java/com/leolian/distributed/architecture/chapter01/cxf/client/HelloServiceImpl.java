package com.leolian.distributed.architecture.chapter01.cxf.client;

import javax.jws.WebService;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/12 16:32
 */
@WebService(endpointInterface = "com.leolian.distributed.architecture.chapter01.cxf.client.HelloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String content) {
        return "Hello, " + content + "!";
    }

}
