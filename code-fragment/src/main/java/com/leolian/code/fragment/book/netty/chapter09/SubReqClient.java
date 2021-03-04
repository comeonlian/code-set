package com.leolian.code.fragment.book.netty.chapter09;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SubReqClient {
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
					ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
					ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
					ch.pipeline().addLast(new SubReqClientHandler());
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
		new SubReqClient().connect(port, "127.0.0.1");
	}
}

class SubReqClientHandler extends SimpleChannelInboundHandler<String> {

	public SubReqClientHandler() {
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		for (int i=0; i<10; i++){
			ctx.write(subReq(i));
		}
		ctx.flush();
	}
	
	private SubscribeReq subReq(int i){
		SubscribeReq req = new SubscribeReq();
		req.setAddress("深圳市南山区软件园");
		req.setPhoneNumber("13766888866");
		req.setProductName("Netty Book For Marshalling");
		req.setSubReqId(i);
		req.setUserName("LianLiang");
		return req;
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Receive server response: [ "+msg+"]");
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