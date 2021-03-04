package com.leolian.chat.server.service;

import java.io.IOException;

import com.leolian.chat.server.context.ChatServerContext;
import com.leolian.chat.server.service.nio.ChatServerNIOService;

/**
 * @Description: 服务构建
 * @author lianliang
 * @date 2017年6月28日 下午1:37:15
 */
public class ChatServerBuilder {
	
	/**
	 * 启动服务
	 * @throws IOException 
	 */
	public void build() throws IOException {
		ChatServerContext context = new ChatServerContext();
		
		if(context.validatorNIOConfig()) {
			// NIO服务
			new ChatServerNIOService(context).start();
		}
		
	}
	
}
