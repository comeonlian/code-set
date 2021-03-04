package com.leolian.code.fragment.book.netty.chapter14.msg;

public class NettyMessage {
	private Header header;
	private Object body;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "NettyMessage [header=" + header + ", body=" + body + "]";
	}
	
}
