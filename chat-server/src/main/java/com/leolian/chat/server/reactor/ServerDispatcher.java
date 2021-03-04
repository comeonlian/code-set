package com.leolian.chat.server.reactor;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerDispatcher {
	private static Logger logger = LoggerFactory.getLogger(ServerDispatcher.class);
	
	private ServerSocketChannel ssc;
	private Selector[] selectors = new Selector[3];

	public ServerDispatcher(ServerSocketChannel ssc, SelectorProvider selectorProvider) throws IOException {
		this.ssc = ssc;
		for (int i = 0; i < 3; i++) {
			selectors[i] = selectorProvider.openSelector();
		}
	}

	public Selector getAcceptSelector() {
		return selectors[0];
	}

	public Selector getReadSelector() {
		return selectors[1];
	}

	public Selector getWriteSelector() {
		return selectors[1];
	}
	
	/**
	 * dispatcher服务
	 * @throws IOException
	 */
	public void execute() throws IOException {
		SocketHandler handler = new SocketAcceptHandler(this, ssc, getAcceptSelector());
		new Thread(handler).start();
		logger.info("server dispatcher start accept hadnler {}", handler);
		handler = new SocketReadHandler(this, ssc, getReadSelector());
		new Thread(handler).start();
		logger.info("server dispatcher start read hadnler {}", handler);
		handler = new SocketWriteHandler(this, ssc, getWriteSelector());
		new Thread(handler).start();
		logger.info("server dispatcher start write hadnler {}", handler);
	}

}