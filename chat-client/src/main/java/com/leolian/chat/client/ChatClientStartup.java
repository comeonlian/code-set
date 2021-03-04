package com.leolian.chat.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leolian.chat.base.common.Commons;
import com.leolian.chat.client.context.ChatClientContext;

public class ChatClientStartup {
	private static Logger logger = LoggerFactory.getLogger(ChatClientStartup.class);
	
	public static void main(String[] args){
		try {
			new ChatClientContext().init(Commons.PORT);
		}catch (Exception e) {
			logger.error("chat client 启动发生异常", e);
		}
	}
}
