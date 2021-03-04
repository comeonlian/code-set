package com.leolian.code.fragment.book.netty.chapter11;

import java.time.LocalDate;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

public class WebSocketServer {

	public void run(final int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast("http-codec", new HttpServerCodec());
					pipeline.addLast("http-aggregator", new HttpObjectAggregator(65536));
					pipeline.addLast("http-chunked", new ChunkedWriteHandler());
					pipeline.addLast("handler", new WebSocketServerHandler());
				}
			});
			ChannelFuture f = b.bind(port).sync();
			System.out.println("Web socket server started at port: "+port);
			System.out.println("Open your browser and navigate to http://127.0.0.1:"+port+"/");
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
		new WebSocketServer().run(port);
	}

}

class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

	private WebSocketServerHandshaker handShaker;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof FullHttpRequest) {
			// 处理HTTP接入
			handleHttpRequest(ctx, (FullHttpRequest)msg);
		} else if (msg instanceof WebSocketFrame) {
			// 处理WebSocket接入
			handleWebSocketFrame(ctx, (WebSocketFrame)msg);
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
	
	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
		// HTTP解码不成功或非WebSocket握手请求
		if(!request.decoderResult().isSuccess() || !("websocket".equals(request.headers().get("Upgrade")))) {
			sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return ;
		}
		WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory("ws://127.0.0.1:8080/websocket", null, false);
		handShaker = factory.newHandshaker(request);
		if(null==handShaker) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			handShaker.handshake(ctx.channel(), request);
		}
	}
	
	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		// 判断是否关闭指令
		if(frame instanceof CloseWebSocketFrame) {
			handShaker.close(ctx.channel(), (CloseWebSocketFrame)frame.retain());
			return ;
		}
		// 判断是否是PING指令
		if(frame instanceof PingWebSocketFrame) {
			ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
		}
		// 判断是否文本消息(二进制消息需要修改编码器)
		if(!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
		}
		
		// 回复消息
		String request = ((TextWebSocketFrame) frame).text();
		System.out.println(String.format("%s received %s", ctx.channel(), request));
		ctx.channel().writeAndFlush(new TextWebSocketFrame(request+", 欢迎使用Netty WebSocket服务，现在是北京时间："+LocalDate.now().toString()));
	}

	private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request,
			DefaultFullHttpResponse response) {
		if(response.status().code()!=200) {
			ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
			response.content().writeBytes(buf);
			buf.release();
			HttpUtil.setContentLength(response, response.content().readableBytes());
		}
		
		ChannelFuture f = ctx.channel().writeAndFlush(response);
		
		// 如果是非Keep-Alive，关闭连接
		if(!HttpUtil.isKeepAlive(request) || response.status().code()!=200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}
	
}