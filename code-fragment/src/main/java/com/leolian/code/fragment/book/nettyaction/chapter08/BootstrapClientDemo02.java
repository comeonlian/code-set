package com.leolian.code.fragment.book.nettyaction.chapter08;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class BootstrapClientDemo02 {

	public static void main(String[] args) {
		final AttributeKey<Integer> id = AttributeKey.newInstance("ID");
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(new NioEventLoopGroup())
		.channel(NioSocketChannel.class)
		.handler(new SimpleChannelInboundHandler<ByteBuf>() {
			
			@Override
			public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
				Integer val = ctx.channel().attr(id).get();
				System.out.println(val);
			}
			
			@Override
			protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
				System.out.println("Received data");
			}
		});
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true)
		.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
		
		bootstrap.attr(id, 123456);
		
		ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("www.manning.com", 80));
		channelFuture.syncUninterruptibly();
	}

}
