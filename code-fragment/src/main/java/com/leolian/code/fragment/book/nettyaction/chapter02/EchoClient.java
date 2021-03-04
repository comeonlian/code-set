package com.leolian.code.fragment.book.nettyaction.chapter02;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {
	private final String host;
	private final int port;
	
	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public static void main(String[] args) throws Exception {
		new EchoClient("127.0.0.1", 8080).start();
	}
	
	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
			.channel(NioSocketChannel.class)
			.remoteAddress(new InetSocketAddress(host, port))
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new EchoClientHandler());
				}
			});
			ChannelFuture channelFuture = bootstrap.connect().sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
	
}
