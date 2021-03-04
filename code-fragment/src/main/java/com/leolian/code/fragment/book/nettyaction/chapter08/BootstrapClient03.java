package com.leolian.code.fragment.book.nettyaction.chapter08;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.oio.OioDatagramChannel;

public class BootstrapClient03 {
	
	public static void main(String[] args) {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(new OioEventLoopGroup())
		.channel(OioDatagramChannel.class)
		.handler(new SimpleChannelInboundHandler<DatagramPacket>() {
			@Override
			protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
				
			}
		});
		
		ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(8080));
		channelFuture.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()) {
					System.out.println("Channel bound");
				} else {
					System.out.println("Bind attempt failed");
					channelFuture.cause().printStackTrace();
				}
			}
		});
		
	}
	
}
