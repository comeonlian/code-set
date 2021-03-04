package com.leolian.code.fragment.book.netty.chapter05;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class FixedEchoServer {
	public void bind(int port) throws Exception {
		// 配置服务端的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 100)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>(){
				@Override
				public void initChannel(SocketChannel ch)throws Exception {
					ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new FixedEchoServerHandler());
				}
			});
			// 绑定端口，同步等待成功
			ChannelFuture f = b.bind(port).sync();
			
			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} finally {
			// 优雅退出，释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {
		int port = 8080;
		if(args!=null && args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			} catch(NumberFormatException e){
				//
			}
		}
		new FixedEchoServer().bind(port);
	}
}

class FixedEchoServerHandler extends SimpleChannelInboundHandler<String> {
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception{
		System.out.println("Receive client: ["+msg+"]");
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}
	
}
