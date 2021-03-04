package com.leolian.distributed.architecture.chapter01.rmi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/12 16:51
 */
public class CustomerSocketFactory extends RMISocketFactory {

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if (port == 0) {
            port = ServiceApp.SERVICE_PORT;
        }
        return new ServerSocket(port);
    }
}
