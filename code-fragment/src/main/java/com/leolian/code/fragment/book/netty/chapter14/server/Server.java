package com.leolian.code.fragment.book.netty.chapter14.server;

import com.leolian.code.fragment.book.netty.chapter14.NettyConstant;
import com.leolian.code.fragment.book.netty.chapter14.codec.NettyMessageDecoder;
import com.leolian.code.fragment.book.netty.chapter14.codec.NettyMessageEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class Server {
	
	public void bind() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup)
		.channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 100)
		.handler(new LoggingHandler(LogLevel.INFO))
		.childHandler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
				pipeline.addLast(new NettyMessageEncoder());
				pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
				pipeline.addLast(new LoginAuthRespHandler());
				pipeline.addLast("heartBeatHandler", new HeartBeatRespHandler());
			}
		});
		
		serverBootstrap.bind(NettyConstant.REMOTE_IP, NettyConstant.REMOTE_PORT);
		System.out.println("Netty server start ok: "+(NettyConstant.REMOTE_IP+":"+NettyConstant.REMOTE_PORT));
	}
	
	public static void main(String[] args) {
		new Server().bind();
	}
	
}
