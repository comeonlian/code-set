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
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.ImmediateEventExecutor;

public class SecureChatServer {
	
	private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup group = new NioEventLoopGroup();
	private Channel channel;
	private SslContext sslContext;
	
	public SecureChatServer(SslContext sslContext) {
		this.sslContext = sslContext;
	}
	
	public ChannelFuture start(InetSocketAddress address) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(group)
		.channel(NioServerSocketChannel.class)
		.childHandler(new SecureChatServerInitializer(channelGroup, sslContext));
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
	
	public static void main(String[] args) throws Exception{
		int port = 9696;
		if(args.length == 1) {
			port = Integer.valueOf(args[0]);
		}
		SelfSignedCertificate cert = new SelfSignedCertificate();
		SslContext context = SslContextBuilder.forClient().keyManager(cert.certificate(), cert.privateKey()).build();
		
		final SecureChatServer server = new SecureChatServer(context);
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
