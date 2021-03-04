package com.leolian.code.fragment.book.distributed.chapter01.tcprpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Provider {

	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(8888);
		
		// 初始化服务实例
		Map<String, Object> services = new HashMap<String, Object>();
		services.put(SayHelloService.class.getName(), new SayHelloServiceImpl());
		try {
			while(true){
				Socket socket = server.accept();
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				String interfaceName = input.readUTF();
				String methodName = input.readUTF();
				Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
				Object[] arguments = (Object[]) input.readObject();
				Class<?> clazz = Class.forName(interfaceName);
				Object service = services.get(interfaceName);
				Method method = clazz.getMethod(methodName, parameterTypes);
				Object result = method.invoke(service, arguments);
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject(result);
			}
		}finally {
			server.close();
		}
	}

}
