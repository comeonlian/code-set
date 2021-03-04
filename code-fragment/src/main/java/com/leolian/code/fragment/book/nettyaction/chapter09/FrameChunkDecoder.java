package com.leolian.code.fragment.book.nettyaction.chapter09;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

public class FrameChunkDecoder extends ByteToMessageDecoder{
	private final int maxFrameSize;
	
	public FrameChunkDecoder(int maxFrameSize) {
		this.maxFrameSize = maxFrameSize;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		int readableBytes = in.readableBytes();
		if(readableBytes > maxFrameSize) {
			in.clear();
			throw new TooLongFrameException();
		}
		ByteBuf buf = in.readBytes(readableBytes);
		out.add(buf);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.err.println("FrameChunkDecoder Exception...");
		cause.printStackTrace();
	}
	
}
