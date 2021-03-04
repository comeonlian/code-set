package com.leolian.chat.server.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketAcceptHandler extends SocketHandler{
	private static Logger logger = LoggerFactory.getLogger(SocketAcceptHandler.class);
	
	public SocketAcceptHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector)
			throws IOException {
		super(dispatcher, sc, selector);
		serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT, this);
	}
	
	@Override
	public void runnerExecute(int readyKeyOps) throws IOException {
		if (readyKeyOps == SelectionKey.OP_ACCEPT) {
			socketChannel = serverSocketChannel.accept();
			socketChannel.configureBlocking(false);
			
			socketChannel.register(dispatcher.getReadSelector(), SelectionKey.OP_READ);
			logger.debug("dispatcher accept handler connect ip: {}", socketChannel.getRemoteAddress());
		}
	}
}
