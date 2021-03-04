package com.leolian.code.fragment.book.netty.chapter14.codec;

import java.io.IOException;

import org.jboss.marshalling.Marshaller;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class MarshallingEncoder {
	private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
	private Marshaller marshaller;
	
	public MarshallingEncoder() throws IOException {
		marshaller = MarshallingCodecFactory.buildMarshalling();
	}
	
	public void encode(Object msg, ByteBuf out) throws Exception {
		try {
			int lengthPos = out.writerIndex();
			out.writeBytes(LENGTH_PLACEHOLDER);
			ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
			marshaller.start(output);
			marshaller.writeObject(msg);
			marshaller.finish();
			System.err.println("lengthPos: "+lengthPos);
			System.err.println("out.writerIndex(): "+out.writerIndex());
			out.setInt(lengthPos, out.writerIndex()-lengthPos-4);
		}finally {
			marshaller.close();
		}
	}
	
}
