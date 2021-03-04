package com.leolian.code.fragment.book.nettyaction.chapter13;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class LogEventBroadcaster {
	private EventLoopGroup group;
	private Bootstrap bootstrap;
	private File file;
	
	public LogEventBroadcaster(InetSocketAddress address, File file) {
		this.group = new NioEventLoopGroup();
		this.file = file;
		this.bootstrap = new Bootstrap();
		this.bootstrap.group(group)
		.channel(NioDatagramChannel.class)
		.option(ChannelOption.SO_BROADCAST, true)
		.handler(new LogEventEncoder(address));
	}
	
	public void run() throws Exception {
		Channel channel = bootstrap.bind(0).sync().channel();
		long pointer = 0;
		for (;;) {
			long len = file.length();
			if(len<pointer) {
				pointer = len;
			}else if(len>pointer) {
				RandomAccessFile raf = new RandomAccessFile(file, "r");
				raf.seek(pointer);
				String line;
				while((line=raf.readLine()) != null) {
					channel.writeAndFlush(new LogEvent(file.getAbsolutePath(), line));
				}
				pointer = raf.getFilePointer();
				raf.close();
			}
			try {
				Thread.sleep(10000);
			}catch (InterruptedException e) {
				Thread.interrupted();
				break;
			}
		}
	}
	
	public void stop() {
		group.shutdownGracefully();
	}
	
	public static void main(String[] args) throws Exception {
		int port = 8686;
		String path = "E:\\Temp\\file\\person.txt";
		LogEventBroadcaster broadcaster = new LogEventBroadcaster(new InetSocketAddress("255.255.255.255", port), new File(path));
		
		try {
			broadcaster.run();
		}finally {
			broadcaster.stop();
		}
		
	}
	
}
