package com.leolian.code.fragment.book.netty.chapter14.codec;

import java.io.IOException;

import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import io.netty.buffer.ByteBuf;

public class MarshallingDecoder {
	private Unmarshaller unmarshaller;

	public MarshallingDecoder() throws IOException {
		this.unmarshaller = MarshallingCodecFactory.buildUnMarshalling();
	}
	
	public Object decode(ByteBuf in) throws Exception {
		int objectSize = in.readInt();
		ByteBuf buf = in.slice(in.readerIndex(), objectSize);
		ByteInput input = new ChannelBufferByteInput(buf);
		try{
			unmarshaller.start(input);
			Object obj = unmarshaller.readObject();
			unmarshaller.finish();
			in.readerIndex(in.readerIndex()+objectSize);
			return obj;
		}finally {
			unmarshaller.close();
		}
	}
	
}
