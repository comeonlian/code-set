package com.leolian.code.fragment.book.distributed.chapter01.httprpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProtocolUtil {
	
	public static Request readRequest(InputStream input) throws IOException {
		byte[] encodeByte = new byte[1];
		input.read(encodeByte);
		byte encode = encodeByte[0];
		byte[] commandLengthBytes = new byte[4];
		input.read(commandLengthBytes);
		int commandLength = ByteUtil.bytes2Int(commandLengthBytes);
		byte[] commandBytes = new byte[commandLength];
		input.read(commandBytes);
		String command = "";
		if(Encode.GBK.getValue()==encode){
			command = new String(commandBytes, "GBK");
		}else{
			command = new String(commandBytes, "UTF8");
		}
		Request request = new Request();
		request.setCommand(command);
		request.setEncode(encode);
		request.setCommandLength(commandLength);
		return request;
	}
	
	public static void writeResponse(OutputStream output, Response response) throws IOException{
		output.write(response.getEncode());
		output.write(ByteUtil.int2ByteArray(response.getResponseLength()));
		if(Encode.GBK.getValue()==response.getEncode()){
			output.write(response.getResponse().getBytes("GBK"));
		}else{
			output.write(response.getResponse().getBytes("UTF8"));
		}
		output.flush();
	}

	public static void writeRequest(OutputStream output, Request request) throws IOException {
		output.write(request.getEncode());
		output.write(ByteUtil.int2ByteArray(request.getCommandLength()));
		if(Encode.GBK.getValue()==request.getEncode()) {
			output.write(request.getCommand().getBytes("GBK"));
		} else {
			output.write(request.getCommand().getBytes("UTF8"));
		}
		output.flush();
	}

	public static Response readResponse(InputStream input) throws IOException {
		byte[] encodeByte = new byte[1];
		input.read(encodeByte);
		byte encode = encodeByte[0];
		byte[] responseLengthBytes = new byte[4];
		input.read(responseLengthBytes);
		int responseLength = ByteUtil.bytes2Int(responseLengthBytes);
		byte[] responseBytes = new byte[responseLength];
		input.read(responseBytes);
		String response = "";
		if(Encode.GBK.getValue()==encode){
			response = new String(responseBytes, "GBK");
		}else{
			response = new String(responseBytes, "UTF8");
		}
		Response res = new Response();
		res.setEncode(encode);
		res.setResponseLength(responseLength);
		res.setResponse(response);
		return res;
	}
}
