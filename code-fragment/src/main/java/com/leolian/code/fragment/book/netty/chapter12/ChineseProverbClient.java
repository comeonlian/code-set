package com.leolian.code.fragment.book.netty.chapter12;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

public class ChineseProverbClient {

	public void run(int port) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioDatagramChannel.class)
			.option(ChannelOption.SO_BROADCAST, true)
			.handler(new ChineseProverbClientHandler());
			
			Channel ch = b.bind(0).sync().channel();
			// 向网段内的所有机器广播UDP消息
			ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("谚语字典查询?", CharsetUtil.UTF_8),
					new InetSocketAddress("255.255.255.255", port))).sync();
			
			if (!ch.closeFuture().await(10000)) {
				System.out.println("查询超时!");
			}
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 8080;
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		new ChineseProverbClient().run(port);
	}
}

class ChineseProverbClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
		String response = packet.content().toString(CharsetUtil.UTF_8);
		System.out.println(response);
		ctx.close();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}