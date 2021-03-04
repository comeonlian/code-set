package com.leolian.code.fragment.book.netty.chapter12;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.ThreadLocalRandom;

public class ChineseProverbServer {
	public void run(int port) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioDatagramChannel.class)
			.option(ChannelOption.SO_BROADCAST, true)
			.handler(new ChineseProverbServerHandler());
			
			b.bind(port).sync().channel().closeFuture().await();
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
		new ChineseProverbServer().run(port);
	}
}

class ChineseProverbServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
	
	private static final String[] DICTIONARY = {
			"只要功夫深，铁棒磨成针。",
			"旧时王谢堂前燕，飞入寻常百姓家。", 
			"洛阳亲友如相问，一片冰心在玉壶。", 
			"一寸光阴一寸金，寸金难买寸光阴。",
			"老骥伏枥，志在千里。烈士暮年，壮心不已!" 
	};
	
	private String nextQuote() {
		int quoteId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
		return DICTIONARY[quoteId];
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
		//DatagramPacket packet = (DatagramPacket) msg;
		String req = packet.content().toString(CharsetUtil.UTF_8);
		System.out.println(req);
		if("谚语字典查询?".equals(req)) {
			System.out.println(packet.sender().getAddress().getHostAddress()+":"+packet.sender().getPort());
			ctx.channel().writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("谚语查询结果："+nextQuote(), CharsetUtil.UTF_8), packet.sender()));
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}
