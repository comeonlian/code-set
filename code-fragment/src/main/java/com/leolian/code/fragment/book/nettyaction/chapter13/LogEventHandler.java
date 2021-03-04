package com.leolian.code.fragment.book.nettyaction.chapter13;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(msg.getReceived());
		builder.append("[");
		builder.append(msg.getSource().toString());
		builder.append("] [");
		builder.append(msg.getLogFile());
		builder.append("]: ");
		builder.append(msg.getMsg());
		System.out.println(builder.toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}
