package com.leolian.chat.server.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 数据处理超类
 * @author lianliang
 * @date 2017年6月30日 下午3:57:24
 */
public abstract class SocketHandler implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(SocketHandler.class);
	
	protected Selector selector;
	protected SocketChannel socketChannel = null;
	protected ServerSocketChannel serverSocketChannel;
	protected ServerDispatcher dispatcher;
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public SocketHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector) throws IOException {
		this.selector = selector;
		this.serverSocketChannel = sc;
		this.dispatcher = dispatcher;
	}

	public abstract void runnerExecute(int readyKeyOps) throws IOException;

	public final void run() {
		while (!Thread.interrupted()) {
			readWriteLock.readLock().lock();
			try {
				int keyOps = selector.select(15);
				if(keyOps>0) {
					//logger.debug("dispatcher handler keys operators count {}", keyOps);
					Set<SelectionKey> readyKeySet = selector.selectedKeys();
					Iterator<SelectionKey> iterator = readyKeySet.iterator();
					while (iterator.hasNext()) {
						SelectionKey key = iterator.next();
						iterator.remove();
						if(key.isValid()) {
							keyOps = key.readyOps();
							if (keyOps == SelectionKey.OP_READ || keyOps == SelectionKey.OP_WRITE) {
								socketChannel = (SocketChannel) key.channel();
								if(socketChannel.isOpen())
									socketChannel.configureBlocking(false);
							}
						}
					}
					runnerExecute(keyOps);
				}
				//Thread.sleep(1000);
			} catch (Exception e) {
				logger.error("handler exception", e);
			} finally {
				readWriteLock.readLock().unlock();
			}
		}
	}
	
	
	/**
	 * 客户端断开连接
	 */
	public void disconnect(){
		try {
			socketChannel.close();
			logger.info("client address=【"+socketChannel.socket().getRemoteSocketAddress()+"】  close.");
		} catch (IOException e) {
			logger.info("client address=【"+socketChannel.socket().getRemoteSocketAddress()+"】 had already closed !!! ");
		}
	}
	
	
	/*private int select() throws IOException {
		int keyOps = selector.select();
		Set<SelectionKey> readyKeySet = selector.selectedKeys();
		Iterator<SelectionKey> iterator = readyKeySet.iterator();
		while (iterator.hasNext()) {
			SelectionKey key = iterator.next();
			iterator.remove();
			keyOps = key.readyOps();
			if (keyOps == SelectionKey.OP_READ || keyOps == SelectionKey.OP_WRITE) {
				socketChannel = (SocketChannel) key.channel();
				socketChannel.configureBlocking(false);
			}
		}
		return keyOps;
	}*/
}
