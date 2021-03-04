package com.leolian.code.fragment.book.netty.chapter09;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Lian
 */
public class SubReqServer {
	public void bind(int port) throws Exception {
		// 配置服务端的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 100)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
					ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
					ch.pipeline().addLast(new SubReqServerHandler());
				}
			});
			
			// 绑定端口，同步等待成功
			ChannelFuture f = b.bind(port).sync();
			
			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} finally {
			// 优雅退出，释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {
		int port = 8080;
		if(args!=null && args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			} catch(NumberFormatException e){
				//
			}
		}
		new SubReqServer().bind(port);
	}
}

class SubReqServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)throws Exception{
		SubscribeReq req = (SubscribeReq) msg;
		if("LianLiang".equalsIgnoreCase(req.getUserName())) {
			System.out.println("Server accept client subscribe req: ["+req.toString()+"]");
			ctx.writeAndFlush(resp(req.getSubReqId()));
		}
	}
	
	private SubscribeResp resp(int subReqId){
		SubscribeResp resp = new SubscribeResp();
		resp.setSubReqId(subReqId);
		resp.setRespCode(0);
		resp.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
		return resp;
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
	}
}