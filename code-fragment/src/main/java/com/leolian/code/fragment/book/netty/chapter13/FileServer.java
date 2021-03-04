package com.leolian.code.fragment.book.netty.chapter13;

import java.io.File;
import java.io.RandomAccessFile;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class FileServer {

	public void run(final int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 100)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new StringDecoder());
					pipeline.addLast(new LineBasedFrameDecoder(1024));
					pipeline.addLast(new StringEncoder());
					pipeline.addLast(new FileServerHandler());
				}
			});
			
			ChannelFuture f = b.bind(port).sync();
			
			System.out.println("Start file server at port: "+port);
			
			f.channel().closeFuture().sync();
		} finally {
			// 优雅退出，释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 8080;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		new FileServer().run(port);
	}

}

class FileServerHandler extends SimpleChannelInboundHandler<String> {

	private static final String CR = System.getProperty("line.separator");
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("Receive msg: "+msg);
		File file = new File(msg);
		if(file.exists()) {
			if(!file.isFile()){
				ctx.writeAndFlush("Not a file: "+file+CR);
				return ;
			}
			ctx.write(file+" "+file.length()+CR);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());
			ctx.write(region);
			ctx.writeAndFlush(CR);
			randomAccessFile.close();
		} else {
			ctx.writeAndFlush("File not found: "+file+CR);
		}
		
		
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}