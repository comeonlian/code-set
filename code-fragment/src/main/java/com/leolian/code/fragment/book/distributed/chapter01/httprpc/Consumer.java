package com.leolian.code.fragment.book.distributed.chapter01.httprpc;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Consumer {

	public static void main(String[] args) throws Exception{
		Socket client = new Socket("127.0.0.1", 4567);
		
		try {
			Request request = new Request();
			request.setCommand("HELLO");
			request.setCommandLength(request.getCommand().length());
			request.setEncode(Encode.UTF8.getValue());
			
			OutputStream output = client.getOutputStream();
			ProtocolUtil.writeRequest(output, request);
			
			InputStream input = client.getInputStream();
			Response response = ProtocolUtil.readResponse(input);
			
			System.out.println("Client received: "+response.getResponse());
		}finally {
			client.close();
		}
	}

}
