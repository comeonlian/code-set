package com.leolian.code.fragment.book.nettyaction.chapter09.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leolian.code.fragment.book.nettyaction.chapter09.FixedLengthFrameDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

public class FixedLengthFrameDecoderTest {
	
	@Test
	public void testFrameDecoders() {
		ByteBuf buf = Unpooled.buffer();
		for (int i = 0; i < 9; i++) {
			buf.writeByte(i);
		}
		ByteBuf input = buf.duplicate();
		EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
		
		assertTrue(channel.writeInbound(input.retain()));
		assertTrue(channel.finish());
		
		ByteBuf read = channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		read = channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		read = channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		assertNull(channel.readInbound());
		buf.release();
	}
	
	@Test
	public void testFrameDecoders2() {
		ByteBuf buf = Unpooled.buffer();
		for (int i = 0; i < 9; i++) {
			buf.writeByte(i);
		}
		
		ByteBuf input = buf.duplicate();
		EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
		
		assertFalse(channel.writeInbound(input.readBytes(2)));
		assertTrue(channel.writeInbound(input.readBytes(6)));
		
		assertTrue(channel.finish());
		
		ByteBuf read = channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		read = channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		read = channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		assertNull(channel.readInbound());
		buf.release();
	}
	
}
