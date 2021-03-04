package chapter02.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	private final int port;
	
	public EchoServer(int port) {
		this.port = port;
	}
	
	public static void main(String[] args) throws Exception {
		int port = 8080;
		if(args.length==1) {
			port = Integer.valueOf(args[0]);
		}
		new EchoServer(port).start();
	}
	
	public void start() throws Exception {
		final EchoServerHandler echoServerHandler = new EchoServerHandler();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(group)
			.channel(NioServerSocketChannel.class)
			.localAddress(new InetSocketAddress(port))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(echoServerHandler);
				};
			});
			ChannelFuture future = serverBootstrap.bind().sync();
			System.out.println(EchoServer.class.getName()+" started and listening for connections on "+future.channel().localAddress());
			future.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
	
}
