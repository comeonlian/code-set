package com.leolian.chat.server.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leolian.chat.base.common.Commons;

/**
 * @Description: 读取Handler
 * @author lianliang
 * @date 2017年6月30日 下午3:56:39
 */
public class SocketReadHandler extends SocketHandler {
	private static Logger logger = LoggerFactory.getLogger(SocketReadHandler.class);

	private int BLOCK = 4096;
	private ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);

	public SocketReadHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector)
			throws IOException {
		super(dispatcher, sc, selector);
	}

	@Override
	public void runnerExecute(int readyKeyOps) throws IOException {
		int count = 0;
		try {
			if (SelectionKey.OP_READ == readyKeyOps) {
				receivebuffer.clear();
				count = socketChannel.read(receivebuffer);
				if (count > 0) {
					logger.debug("dispatcher readable handler.");
					receivebuffer.flip();
					byte[] bytes = new byte[receivebuffer.remaining()];
					receivebuffer.get(bytes);
					String body = new String(bytes, Commons.CHARSET);
					logger.debug("readable handler receive: {}", body);
					socketChannel.register(dispatcher.getWriteSelector(), SelectionKey.OP_WRITE);
				} else if (count==-1) {
					disconnect();
				}
			}
		} catch (Exception e) {
			logger.error("client connection closed ", e);
			disconnect();
		}
	}
}
