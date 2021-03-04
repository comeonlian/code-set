package com.leolian.code.fragment.book.netty.chapter14.client;

import com.leolian.code.fragment.book.netty.chapter14.msg.Header;
import com.leolian.code.fragment.book.netty.chapter14.msg.MessageType;
import com.leolian.code.fragment.book.netty.chapter14.msg.NettyMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginAuthReqHandler extends SimpleChannelInboundHandler<NettyMessage>{
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buildLoginReq());
	}
	
	/**
	 * 链路建立之后发送握手消息
	 * @return
	 */
	private NettyMessage buildLoginReq() {
		NettyMessage msg = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.LOGIN_REQ.value());
		msg.setHeader(header);
		return msg;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, NettyMessage msg) throws Exception {
		if(msg.getHeader()!=null && msg.getHeader().getType()==MessageType.LOGIN_RESP.value()){
			byte body = (byte) msg.getBody();
			if(body!=(byte)0){
				ctx.close();
			}else{
				System.out.println("Login is ok: "+msg);
				ctx.fireChannelRead(msg);
			}
		}else{
			ctx.fireChannelRead(msg); //透传给后面的handler处理
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.fireExceptionCaught(cause);
	}
	
}
