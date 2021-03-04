package com.leolian.code.fragment.book.nettyaction.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Description: 变长解码器
 * @author lianliang
 * @date 2017年9月21日 下午2:23:16
 */
public class LengthBasedInitializer extends ChannelInitializer<Channel> {
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new LengthFieldBasedFrameDecoder(64 * 1024, 0, 8));
		pipeline.addLast(new FrameHandler());
	}

	public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {
		@Override
		public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
			// Do something with the frame
		}
	}
}