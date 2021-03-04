package com.leolian.code.fragment.book.netty.chapter14.server;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.leolian.code.fragment.book.netty.chapter14.msg.Header;
import com.leolian.code.fragment.book.netty.chapter14.msg.MessageType;
import com.leolian.code.fragment.book.netty.chapter14.msg.NettyMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginAuthRespHandler extends SimpleChannelInboundHandler<NettyMessage>{
	
	private Map<String, Boolean> nodeChecked = new ConcurrentHashMap<>();
	private String[] whiteList = {"127.0.0.1", "192.168.1.66"};
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, NettyMessage msg) throws Exception {
		if(msg.getHeader()!=null && msg.getHeader().getType()==MessageType.LOGIN_REQ.value()){
			String nodeIndex = ctx.channel().remoteAddress().toString();
			NettyMessage loginResp = null;
			if(nodeChecked.containsKey(nodeIndex)){
				// 重复登录
				loginResp = buildResponse((byte)-1);
			}else{
				InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
				String ip = address.getAddress().getHostAddress();
				boolean isOk = false;
				for (String wip : whiteList) {
					if(wip.equals(ip)){
						isOk = true;
						break;
					}
				}
				loginResp = isOk? buildResponse((byte)0):buildResponse((byte)-1);
				if(isOk)
					nodeChecked.put(nodeIndex, true);
			}
			System.out.println("The login response is: "+loginResp);
			ctx.writeAndFlush(loginResp);
		}else{
			ctx.fireChannelRead(msg);
		}
	}
	
	private NettyMessage buildResponse(byte b) {
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.LOGIN_RESP.value());
		message.setHeader(header);
		message.setBody(b);
		return message;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		nodeChecked.remove(ctx.channel().remoteAddress().toString()); //删除缓存的登录状态
		ctx.close();
		ctx.fireExceptionCaught(cause);
	}
	
}
