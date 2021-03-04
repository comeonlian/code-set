package com.leolian.code.fragment.book.nettyaction.chapter08;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class BootstrapServerDemo02 {
	
	public static void main(String[] args) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
		.channel(NioServerSocketChannel.class)
		.childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
			ChannelFuture channelFuture;
			
			@Override
			public void channelActive(ChannelHandlerContext ctx) throws Exception {
				Bootstrap bootstrap = new Bootstrap();
				bootstrap.channel(NioSocketChannel.class)
				.handler(new SimpleChannelInboundHandler<ByteBuf>() {
					@Override
					protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
						System.out.println("Received data");
					}
				});
				bootstrap.group(ctx.channel().eventLoop());
				channelFuture = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
			}
			
			@Override
			protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
				if(channelFuture.isDone()) {
					
				}
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
