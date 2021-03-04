package com.leolian.code.fragment.book.nettyaction.chapter05;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufDemo02 {
	
	public static void main(String[] args) {
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
		System.out.println((char)buf.readByte());
		int readerIndex = buf.readerIndex();
		int writerIndex = buf.writerIndex();
		buf.writeByte((byte)'?');
		assert readerIndex == buf.readerIndex();
		assert writerIndex != buf.writerIndex();
	}
	
}
