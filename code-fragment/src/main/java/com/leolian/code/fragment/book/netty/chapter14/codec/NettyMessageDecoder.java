package com.leolian.code.fragment.book.netty.chapter14.codec;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.leolian.code.fragment.book.netty.chapter14.msg.Header;
import com.leolian.code.fragment.book.netty.chapter14.msg.NettyMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {
	
	private MarshallingDecoder marshallingDecoder;
	
	public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
		marshallingDecoder = new MarshallingDecoder();
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if(null==frame) {
			return null;
		}
		// DEBUG
		byte[] bytes = new byte[frame.readableBytes()];
		frame.readBytes(bytes);
		System.err.println("Dncoder: "+Arrays.toString(bytes));
		frame.clear();
		frame.writeBytes(bytes);
		
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setCrcCode(frame.readInt());
		header.setLength(frame.readInt());
		header.setSessionId(frame.readLong());
		header.setType(frame.readByte());
		header.setPriority(frame.readByte());
		
		int size = frame.readInt();
		if(size>0) {
			Map<String, Object> attachment = new HashMap<String, Object>(size);
			int keySize = 0;
			byte[] keyArray = null;
			String key = null;
			for (int i = 0; i < size; i++) {
				keySize = frame.readInt();
				keyArray = new byte[keySize];
				frame.readBytes(keyArray);
				key = new String(keyArray, "UTF-8");
				attachment.put(key, marshallingDecoder.decode(frame));
			}
			keyArray = null;
			key = null;
			header.setAttachment(attachment);
		}
		if(frame.readableBytes()>4) {
			message.setBody(marshallingDecoder.decode(frame));
		}
		message.setHeader(header);
		return message;
	}
	
}
