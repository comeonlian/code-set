package com.leolian.distributed.framework.chapter01;

import com.leolian.distributed.framework.chapter01.client.RpcImporter;
import com.leolian.distributed.framework.chapter01.server.RpcExporter;
import com.leolian.distributed.framework.chapter01.service.EchoService;
import com.leolian.distributed.framework.chapter01.service.EchoServiceImpl;

import java.net.InetSocketAddress;

/**
 * @description: 
 * @author lianliang
 * @date 2018/12/11 14:00
 */
public class RpcClient {

    public static void main(String[] args) throws Exception {
        // 客户端连接
        RpcImporter<EchoService> rpcImporter = new RpcImporter<EchoService>();
        EchoService echoServiceImpl = rpcImporter.importer(EchoServiceImpl.class, new InetSocketAddress("127.0.0.1", 8385));
        String result = echoServiceImpl.echo("Are you ok ?");
        System.out.println(result);
    }
    
}
