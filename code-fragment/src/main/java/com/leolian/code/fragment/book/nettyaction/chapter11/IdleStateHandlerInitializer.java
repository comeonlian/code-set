package com.leolian.code.fragment.book.nettyaction.chapter11;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * @Description: 使用IdleStateHandler 来测试远程节点是否仍然还活着，并且在它失活时通过关闭连接来释放资源
 * @author lianliang
 * @date 2017年9月21日 上午11:24:57
 */
public class IdleStateHandlerInitializer extends ChannelInitializer<Channel> {
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS));
		pipeline.addLast(new HeartbeatHandler());
	}

	public static final class HeartbeatHandler extends ChannelInboundHandlerAdapter {
		private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled
				.unreleasableBuffer(Unpooled.copiedBuffer("HEARTBEAT", CharsetUtil.ISO_8859_1));

		@Override
		public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
			if (evt instanceof IdleStateEvent) {
				ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
			} else {
				super.userEventTriggered(ctx, evt);
			}
		}
	}
}