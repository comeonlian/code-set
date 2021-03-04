package com.leolian.code.fragment.book.nettyaction.chapter08;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class BootstrapServerDemo01 {
	
	public static void main(String[] args) {
		EventLoopGroup group = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		bootstrap.group(group)
		.channel(NioServerSocketChannel.class)
		.childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
			@Override
			protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
				System.out.println("Received data");
			}
		});
		
		ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(8080));
		channelFuture.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()) {
					System.out.println("Server bound");
				} else {
					System.out.println("Bind attempt failed");
					future.cause().printStackTrace();
				}
			}
		});
	}
	
}
