package com.leolian.code.fragment.book.nettyaction.chapter08;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class BootstrapClientDemo01 {
	
	public static void main(String[] args) {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group)
		.channel(NioSocketChannel.class)
		.handler(new SimpleChannelInboundHandler<ByteBuf>() {
			@Override
			protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
				System.out.println("Received data");
			}
		});
		ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
		channelFuture.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(channelFuture.isSuccess()) {
					System.out.println("Connection established");
				} else {
					System.err.println("Connection attempt failed");
					channelFuture.cause().printStackTrace();
				}
			}
		});
	}
	
}
