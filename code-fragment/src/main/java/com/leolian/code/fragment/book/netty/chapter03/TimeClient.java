package com.leolian.code.fragment.book.netty.chapter03;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
	public void connect(int port, String host) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>(){
				@Override
				public void initChannel(SocketChannel ch)throws Exception{
					ch.pipeline().addLast(new TimeClientHandler());
				}
			});
			
			// 发起异步连接操作
			ChannelFuture f = b.connect(host, port).sync();
			
			// 等待客户端链路关闭
			f.channel().closeFuture().sync();
		}finally{
			// 优雅退出，释放NIO线程组
			group.shutdownGracefully();
		}
	}
	
	public static void main (String[] args)throws Exception {
		int port = 8080;
		if(args!=null && args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			} catch(NumberFormatException e){
				//
			}
		}
		new TimeClient().connect(port, "127.0.0.1");
	}
}

class TimeClientHandler extends SimpleChannelInboundHandler<String> {
	
	private final ByteBuf firstMessage;
	
	public TimeClientHandler(){
		byte[] req = "QUERY TIME ORDER".getBytes();
		firstMessage = Unpooled.buffer(req.length);
		firstMessage.writeBytes(req);
	}
	
	public void channelActive(ChannelHandlerContext ctx){
		ctx.channel().writeAndFlush(ctx.writeAndFlush(firstMessage));
	}
	
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception{
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		System.out.println("Now is: "+body);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		System.out.println("Unexcepted exceptiuon from downstream: "+cause.getMessage());
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
	}
	
}