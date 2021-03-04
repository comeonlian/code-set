package com.leolian.distributed.framework.chapter01.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcImporter<T> {

    @SuppressWarnings("unchecked")
    public T importer(final Class<?> serviceClass, final InetSocketAddress address) throws Exception {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                serviceClass.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
                        Socket socket = null;
                        ObjectInputStream input = null;
                        ObjectOutputStream output = null;
                        try {
                            socket = new Socket();
                            System.out.println("connect address( " + address.getHostName() + ":" + address.getPort() + " )");
                            socket.connect(address);
                            output = new ObjectOutputStream(socket.getOutputStream());
                            output.writeUTF(serviceClass.getName());
                            output.writeUTF(method.getName());
                            output.writeObject(method.getParameterTypes());
                            output.writeObject(args);
                            input = new ObjectInputStream(socket.getInputStream());
                            return input.readObject();
                        } finally {
                            if (output != null)
                                output.close();
                            if (input != null)
                                input.close();
                            if (socket != null)
                                socket.close();
                        }
                    }
                }
        );
    }

}