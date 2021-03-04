package com.leolian.code.fragment.book.distributed.chapter01.httprpc;

public enum Encode {
	GBK((byte)0),
	UTF8((byte)1);
	
	private byte encode;
	
	private Encode(byte encode) {
		this.encode = encode;
	}
	
	public byte getValue() {
		return encode;
	}
}
