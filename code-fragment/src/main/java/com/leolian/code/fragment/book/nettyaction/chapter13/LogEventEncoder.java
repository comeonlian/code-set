package com.leolian.code.fragment.book.nettyaction.chapter13;

import java.net.InetSocketAddress;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

public class LogEventEncoder extends MessageToMessageEncoder<LogEvent> {
	private InetSocketAddress remoteAddress;
	
	public LogEventEncoder(InetSocketAddress remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, LogEvent logEvent, List<Object> out) throws Exception {
		byte[] file = logEvent.getLogFile().getBytes(CharsetUtil.UTF_8);
		byte[] msg = logEvent.getMsg().getBytes(CharsetUtil.UTF_8);
		ByteBuf byteBuf = ctx.alloc().buffer(file.length+msg.length+1);
		byteBuf.writeBytes(file);
		byteBuf.writeByte(LogEvent.SEPARATOR);
		byteBuf.writeBytes(msg);
		out.add(new DatagramPacket(byteBuf, remoteAddress));
	}

}
