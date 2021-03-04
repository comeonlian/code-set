package com.leolian.distributed.framework.chapter01;

import com.leolian.distributed.framework.chapter01.server.RpcExporter;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/11 14:13
 */
public class RpcServer {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8385;

    public static void main(String[] args) throws Exception {
        // RPC服务端
        RpcExporter.exporter(HOST, PORT);
    }
}
