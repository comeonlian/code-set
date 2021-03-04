package com.leolian.code.fragment.book.nettyaction.chapter13;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket>{

	@Override
	protected void decode(ChannelHandlerContext ctx, DatagramPacket datagramPacket, List<Object> out) throws Exception {
		ByteBuf content = datagramPacket.content();
		int index = content.indexOf(0, content.readableBytes(), LogEvent.SEPARATOR);
		String filename = content.slice(0, index).toString(CharsetUtil.UTF_8);
		String msg = content.slice(index+1, content.readableBytes()).toString(CharsetUtil.UTF_8);
		LogEvent event = new LogEvent(datagramPacket.sender(), System.currentTimeMillis()/1000, filename, msg);
		out.add(event);
	}
	
}
