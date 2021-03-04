package com.leolian.code.fragment.book.netty.chapter14.server;

import com.leolian.code.fragment.book.netty.chapter14.msg.Header;
import com.leolian.code.fragment.book.netty.chapter14.msg.MessageType;
import com.leolian.code.fragment.book.netty.chapter14.msg.NettyMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatRespHandler extends SimpleChannelInboundHandler<NettyMessage> {
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, NettyMessage msg) throws Exception {
		if(msg.getHeader()!=null && msg.getHeader().getType()==MessageType.HEARTBEAT_REQ.value()){
			System.out.println("Receive client heart beat message: "+msg);
			NettyMessage heartBeat = buildHeartBeat();
			System.out.println("Send heart beat response message to client: "+heartBeat);
			ctx.writeAndFlush(heartBeat);
		}else{
			ctx.fireChannelRead(msg);
		}
	}

	private NettyMessage buildHeartBeat() {
		NettyMessage msg = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.HEARTBEAT_RESP.value());
		msg.setHeader(header);
		return msg;
	}

}
