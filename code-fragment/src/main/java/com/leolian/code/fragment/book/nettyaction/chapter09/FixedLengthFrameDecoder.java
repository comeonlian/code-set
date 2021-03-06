package com.leolian.code.fragment.book.nettyaction.chapter09;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class FixedLengthFrameDecoder extends ByteToMessageDecoder{
	private int frameLength;
	
	public FixedLengthFrameDecoder(int frameLength) {
		if(frameLength<=0) {
			throw new IllegalArgumentException("frameLength must be a positive integer: "+frameLength);
		}
		this.frameLength = frameLength;
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		while(in.readableBytes()>=frameLength) {
			ByteBuf buf = in.readBytes(frameLength);
			out.add(buf);
		}
	}
	
}
