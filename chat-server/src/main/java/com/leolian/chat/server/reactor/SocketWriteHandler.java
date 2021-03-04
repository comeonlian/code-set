package com.leolian.chat.server.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 写入Handler
 * @author lianliang
 * @date 2017年6月30日 下午3:58:00
 */
public class SocketWriteHandler extends SocketHandler {
	private static Logger logger = LoggerFactory.getLogger(SocketWriteHandler.class);
	
	private int BLOCK = 4096;
	private ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
	private static int Index = 1;

	public SocketWriteHandler(ServerDispatcher dispatcher, ServerSocketChannel sc, Selector selector)
			throws IOException {
		super(dispatcher, sc, selector);
	}

	@Override
	public void runnerExecute(int readyKeyOps) throws IOException {
		try {
			if (readyKeyOps == SelectionKey.OP_WRITE) {
				logger.debug("dispatcher writable handler.");
				String data = String.format("%d", Index);
				byte[] req = data.getBytes();
				sendbuffer.clear();
				
				logger.debug("writable handler return : {}", data);
				
				sendbuffer = ByteBuffer.allocate(req.length);
				sendbuffer.put(req);
				sendbuffer.flip();
				socketChannel.write(sendbuffer);
				Index++;
				socketChannel.register(dispatcher.getReadSelector(), SelectionKey.OP_READ);
			}
		} catch (Exception e) {
			logger.error("client connection closed ", e);
			disconnect();
		}
	}
}
