package com.leolian.code.fragment.book.distributed.chapter01.tcprpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class Consumer {
	
	public static void main(String[] args) throws Exception{
		Socket socket = new Socket("127.0.0.1", 8888);
		try {
			Object[] arguments = {"Leo"};
			String interfaceName = SayHelloService.class.getName();
			Method method = SayHelloService.class.getMethod("sayHello", java.lang.String.class);
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeUTF(interfaceName);
			output.writeUTF(method.getName());
			output.writeObject(method.getParameterTypes());
			output.writeObject(arguments);
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			Object result = input.readObject();
			System.out.println(result);
		}finally {
			socket.close();
		}
	}
	
}
