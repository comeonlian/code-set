package com.leolian.code.fragment.book.nettyaction.chapter09.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leolian.code.fragment.book.nettyaction.chapter09.AbsIntegerEncoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

public class AbsIntegerEncoderTest {
	
	@Test
	public void testEncoder() {
		ByteBuf buf = Unpooled.buffer();
		for(int i=1; i<10; i++) {
			buf.writeInt(i * -1);
		}
		
		EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
		
		assertTrue(channel.writeOutbound(buf));
		assertTrue(channel.finish());
		
		for(int i=1; i<10; i++) {
			assertEquals(i, (int)channel.readOutbound());
		}
		
		assertNull(channel.readOutbound());
	}
	
}
