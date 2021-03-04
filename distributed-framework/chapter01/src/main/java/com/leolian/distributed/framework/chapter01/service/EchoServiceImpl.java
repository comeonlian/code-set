package com.leolian.distributed.framework.chapter01.service;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/11 11:05
 */
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String ping) {
        return ping != null ? ping + "--> I am ok." : "I am ok.";
    }
    
}
