package com.leolian.code.fragment.book.netty.chapter04;

import java.util.Date;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Lian
 */
public class TimeServerBug {
	public void bind(int port) throws Exception {
		// 配置服务端的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 1024)
			.childHandler(new ChildChannelHandler());
			
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
	
	private class ChildChannelHandler extends ChannelInitializer<NioSocketChannel>{
		@Override
		protected void initChannel(NioSocketChannel ch) throws Exception {
			ch.pipeline().addLast(new TimeServerHandler());
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
		new TimeServerBug().bind(port);
	}
}

/**
 * 《Netty权威指南》中使用的是Netty5.x，Handler继承ChannelHandlerAdapter
 * Netty4.x中handler需要继承SimpleChannelInboundHandler
 * @author Lian
 */
class TimeServerHandler extends SimpleChannelInboundHandler<String> {
	
	private int counter;
	
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception{
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8")
				.substring(0, req.length-System.getProperty("line.separator").length());
		System.out.println("The time server receive order: "+body+" ; the counter is: "+(++counter));
		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? 
				new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
		currentTime = currentTime + System.getProperty("line.separator");
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.write(resp);
	}
	
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
	}
}