package com.leolian.distributed.architecture.chapter01.rpc.framework.invoke;

import com.leolian.distributed.architecture.chapter01.rpc.framework.ProviderReflect;
import com.leolian.distributed.architecture.chapter01.rpc.framework.service.HelloService;
import com.leolian.distributed.architecture.chapter01.rpc.framework.service.HelloServiceImpl;

public class RpcProviderMain {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service, 8083);
    }

}