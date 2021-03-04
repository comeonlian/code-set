package com.leolian.code.fragment.book.nettyaction.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * 
 * @Description: 
 * 传入数据流是一系列的帧，每个帧都由换行符（\n）分隔；
 * 每个帧都由一系列的元素组成，每个元素都由单个空格字符分隔；
 * 一个帧的内容代表一个命令，定义为一个命令名称后跟着数目可变的参数。
 * @author lianliang
 * @date 2017年9月21日 上午11:46:27
 */
public class CmdHandlerInitializer extends ChannelInitializer<Channel> {
	final static byte SPACE = (byte) ' ';

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new CmdDecoder(64 * 1024));
		pipeline.addLast(new CmdHandler());
	}

	public static final class Cmd {
		private final ByteBuf name;
		private final ByteBuf args;

		public Cmd(ByteBuf name, ByteBuf args) {
			this.name = name;
			this.args = args;
		}

		public ByteBuf name() {
			return name;
		}

		public ByteBuf args() {
			return args;
		}
	}

	public static final class CmdDecoder extends LineBasedFrameDecoder {
		public CmdDecoder(int maxLength) {
			super(maxLength);
		}

		@Override
		protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
			ByteBuf frame = (ByteBuf) super.decode(ctx, buffer);
			if (frame == null) {
				return null;
			}
			int index = frame.indexOf(frame.readerIndex(), frame.writerIndex(), SPACE);
			return new Cmd(frame.slice(frame.readerIndex(), index), frame.slice(index + 1, frame.writerIndex()));
		}
	}

	public static final class CmdHandler extends SimpleChannelInboundHandler<Cmd> {
		@Override
		public void channelRead0(ChannelHandlerContext ctx, Cmd msg) throws Exception {
			// Do something with the command
		}
	}
}