package com.leolian.code.fragment.book.netty.chapter04;

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
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClientRepair {
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
					ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new TimeClientHandlerRepair());
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
		new TimeClientRepair().connect(port, "127.0.0.1");
	}
}

class TimeClientHandlerRepair extends SimpleChannelInboundHandler<String> {
	private int counter;
	
	private byte[] req;
	
	public TimeClientHandlerRepair(){
		req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
	}
	
	public void channelActive(ChannelHandlerContext ctx){
		ByteBuf message = null;
		for(int i=0; i<100; i++){
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}
	
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception{
		String body = (String) msg;
		System.out.println("Now is: "+body+"; the counter is: "+(++counter));
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