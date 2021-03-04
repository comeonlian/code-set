package com.leolian.code.fragment.book.distributed.chapter01.httprpc;
public class Response {
	private byte encode;
	
	private int responseLength;
	
	private String response;
	
	public byte getEncode() {
		return encode;
	}

	public void setEncode(byte encode) {
		this.encode = encode;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getResponseLength() {
		return responseLength;
	}

	public void setResponseLength(int responseLength) {
		this.responseLength = responseLength;
	}

	@Override
	public String toString() {
		return "Response [encode=" + encode + ", response=" + response + ", responseLength=" + responseLength + "]";
	}
	
}