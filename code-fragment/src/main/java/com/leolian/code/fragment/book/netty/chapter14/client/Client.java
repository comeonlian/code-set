package com.leolian.code.fragment.book.netty.chapter14.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.leolian.code.fragment.book.netty.chapter14.NettyConstant;
import com.leolian.code.fragment.book.netty.chapter14.codec.NettyMessageDecoder;
import com.leolian.code.fragment.book.netty.chapter14.codec.NettyMessageEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class Client {
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	EventLoopGroup group = new NioEventLoopGroup();
	
	public void connect(int port, String host) throws Exception {
		try{
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new NettyMessageDecoder(1024 * 1024, 4, 21));
					pipeline.addLast("messageEncoder", new NettyMessageEncoder());
					pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
					pipeline.addLast("loginAuthHandler", new LoginAuthReqHandler());
					pipeline.addLast("heartBeatHandler", new HeartBeatReqHandler());
				}
			});
			
			ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port)).sync();
			future.channel().closeFuture().sync();
		} finally {
			// 所有资源释放完成之后，清空资源，再次发起重连
			executor.execute(new Runnable(){
				@Override
				public void run() {
					try{
						TimeUnit.SECONDS.sleep(2);
					}catch (Exception e) {
						e.printStackTrace();
					}
					try {
						connect(NettyConstant.REMOTE_PORT, NettyConstant.REMOTE_IP);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Client().connect(NettyConstant.REMOTE_PORT, NettyConstant.REMOTE_IP);
	}
	
}
