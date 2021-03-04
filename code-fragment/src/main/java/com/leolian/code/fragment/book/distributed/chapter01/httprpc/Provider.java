package com.leolian.code.fragment.book.distributed.chapter01.httprpc;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Provider {
	
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(4567);
		try {
			while(true){
				Socket client = server.accept();
				InputStream input = client.getInputStream();
				Request request = ProtocolUtil.readRequest(input);
				System.out.println("Server received: "+request.getCommand());
				OutputStream output = client.getOutputStream();
				Response response = new Response();
				response.setEncode(Encode.UTF8.getValue());
				if(request.getCommand().equalsIgnoreCase("HELLO")){
					response.setResponse("hello!");
				}else{
					response.setResponse("bye bye!");
				}
				response.setResponseLength(response.getResponse().length());
				ProtocolUtil.writeResponse(output, response);
			}
		}finally {
			server.close();
		}
	}
	
}
