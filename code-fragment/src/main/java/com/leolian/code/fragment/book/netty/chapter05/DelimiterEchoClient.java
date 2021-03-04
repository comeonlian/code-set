package com.leolian.code.fragment.book.netty.chapter05;

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
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class DelimiterEchoClient {
	public void connect(int port, String host) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
					ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new DelimiterEchoClientHandler());
				}
			});
			
			// 发起异步连接操作
			ChannelFuture f = b.connect(host, port).sync();
			
			// 等待客户端链路关闭
			f.channel().closeFuture().sync();
		} finally {
			// 优雅退出，释放NIO线程组
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 8080;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				//
			}
		}
		new DelimiterEchoClient().connect(port, "127.0.0.1");
	}
}

class DelimiterEchoClientHandler extends SimpleChannelInboundHandler<String> {
	private int counter = 0;

	private String echo_req = "Hi, LianLiang. Welcome to Netty.$_";

	public DelimiterEchoClientHandler() {
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		for (int i = 0; i < 10; i++) {
			ctx.channel().writeAndFlush(Unpooled.copiedBuffer(echo_req.getBytes()));
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
	}
	
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("This is: " + (++counter) + " times receive server: [" + msg + "]");
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}