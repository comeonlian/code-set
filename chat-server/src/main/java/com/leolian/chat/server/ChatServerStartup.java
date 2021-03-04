package com.leolian.chat.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leolian.chat.server.context.ChatServerContext;
import com.leolian.chat.server.service.ChatServerBuilder;

/**
 * @Description: 服务端启动程序
 * @author lianliang
 * @date 2017年6月27日 下午4:01:32
 */
public class ChatServerStartup {
	private static Logger logger = LoggerFactory.getLogger(ChatServerContext.class);
	
	public static void main(String[] args){
		try {
			new ChatServerBuilder().build();
		}catch (Exception e) {
			logger.error("chat server 启动发生异常", e);
		}
	}
	
}