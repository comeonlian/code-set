package com.leolian.code.fragment.book.nettyaction.chapter13;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class LogEventMonitor {
	private EventLoopGroup group;
	private Bootstrap bootstrap;
	
	public LogEventMonitor(InetSocketAddress address) {
		this.group = new NioEventLoopGroup();
		this.bootstrap = new Bootstrap();
		this.bootstrap.group(group)
		.channel(NioDatagramChannel.class)
		.option(ChannelOption.SO_BROADCAST, true)
		.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast(new LogEventDecoder());
				ch.pipeline().addLast(new LogEventHandler());
			}
		})
		.localAddress(address);
	}
	
	public Channel bind() {
		return bootstrap.bind().syncUninterruptibly().channel();
	}
	
	public void stop() {
		this.group.shutdownGracefully();
	}
	
	public static void main(String[] args) throws Exception {
		int port = 8585;
		LogEventMonitor monitor = new LogEventMonitor(new InetSocketAddress(port));
		try {
			Channel channel = monitor.bind();
			System.out.println("LogEventMonitor running.");
			channel.closeFuture().sync();
		}finally {
			monitor.stop();
		}
	}
	
}
