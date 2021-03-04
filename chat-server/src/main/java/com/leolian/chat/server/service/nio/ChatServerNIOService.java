package com.leolian.chat.server.service.nio;

import java.io.IOException;

import com.leolian.chat.server.context.ChatServerContext;
import com.leolian.chat.server.reactor.ServerReactor;

/**
 * @Description: NIO服务
 * @author lianliang
 * @date 2017年6月28日 下午1:49:07
 */
public class ChatServerNIOService {
	private ChatServerContext context = null;
	
	public ChatServerNIOService(ChatServerContext context) {
		this.context = context;
	}
	
	/**
	 * 开启Reactor模式
	 * @throws IOException
	 */
	public void start() throws IOException {
		new Thread(new ServerReactor(context.getPort())).start();
	}
	
}
