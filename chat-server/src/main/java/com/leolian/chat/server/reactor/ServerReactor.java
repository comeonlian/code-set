package com.leolian.chat.server.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerReactor implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(ServerReactor.class);
	
	private SelectorProvider selectorProvider = SelectorProvider.provider();
	private ServerSocketChannel serverSocketChannel;

	public ServerReactor(int port) throws IOException {
		serverSocketChannel = selectorProvider.openServerSocketChannel(); // ServerSocketChannel.open();
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress("localhost", port), 1024);
		serverSocketChannel.configureBlocking(false);
		logger.info(String.format("server start port %d .", port));
	}
	
	public void run() {
		try {
			new ServerDispatcher(serverSocketChannel, SelectorProvider.provider()).execute();
		} catch (IOException e) {
			logger.error("server reactor exception {}", e);
		}
	}
}