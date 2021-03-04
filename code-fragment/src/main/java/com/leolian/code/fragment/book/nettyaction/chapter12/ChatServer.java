package com.leolian.code.fragment.book.nettyaction.chapter12;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

public class ChatServer {
	
	private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup group = new NioEventLoopGroup();
	private Channel channel;
	
	public ChannelFuture start(InetSocketAddress address) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(group)
		.channel(NioServerSocketChannel.class)
		.childHandler(new ChatServerInitializer(channelGroup));
		ChannelFuture channelFuture = bootstrap.bind(address);
		channelFuture.syncUninterruptibly();
		channel = channelFuture.channel();
		return channelFuture;
	}
	
	public void destroy() {
		if(null!=null) {
			channel.close();
		}
		channelGroup.close();
		group.shutdownGracefully();
	}
	
	public static void main(String[] args) {
		int port = 8787;
		if(args.length == 1) {
			port = Integer.valueOf(args[0]);
		}
		final ChatServer server = new ChatServer();
		ChannelFuture future = server.start(new InetSocketAddress(port));
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				server.destroy();
			}
		});
		future.channel().closeFuture().syncUninterruptibly();
	}
	
}
