package com.leolian.code.fragment.book.netty.chapter14.client;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.leolian.code.fragment.book.netty.chapter14.msg.Header;
import com.leolian.code.fragment.book.netty.chapter14.msg.MessageType;
import com.leolian.code.fragment.book.netty.chapter14.msg.NettyMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatReqHandler extends SimpleChannelInboundHandler<NettyMessage> {
	
	private volatile ScheduledFuture<?> heartBeat;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, NettyMessage msg) throws Exception {
		if(msg.getHeader()!=null && msg.getHeader().getType()==MessageType.LOGIN_RESP.value()){
			heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
		}else if(msg.getHeader()!=null && msg.getHeader().getType()==MessageType.HEARTBEAT_RESP.value()){
			System.out.println("Client receive server heart beat message: "+msg);
		}else{
			ctx.fireChannelRead(msg);
		}
	}
	
	private class HeartBeatTask implements Runnable {
		private ChannelHandlerContext ctx;
		
		public HeartBeatTask(ChannelHandlerContext ctx){
			this.ctx = ctx;
		}
		
		@Override
		public void run() {
			NettyMessage heartBeat = buildHeartBeat();
			System.out.println("Client send heart beat message to server: "+heartBeat);
			ctx.writeAndFlush(heartBeat);
		}
		
		private NettyMessage buildHeartBeat() {
			NettyMessage msg = new NettyMessage();
			Header header = new Header();
			header.setType(MessageType.HEARTBEAT_REQ.value());
			msg.setHeader(header);
			return msg;
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		if(heartBeat!=null){
			heartBeat.cancel(true);
			heartBeat = null;
		}
		ctx.fireExceptionCaught(cause);
	}
	
}
