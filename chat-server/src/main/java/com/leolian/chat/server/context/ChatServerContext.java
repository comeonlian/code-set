package com.leolian.chat.server.context;

import com.leolian.chat.base.common.Commons;

/**
 * @Description: ChatServer上下文
 * @author lianliang
 * @date 2017年6月28日 下午1:50:56
 */
public class ChatServerContext {
	private int port;
	
	
	public ChatServerContext() {
		init();
	}
	
	/**
	 * 初始化方法
	 */
	private void init() {
		this.port = Commons.PORT;
	}
	
	/**
	 * 校验配置文件
	 * @return
	 */
	public boolean validatorNIOConfig() {
		
		return true;
	}
	
	
	/* ***************		getter方法		************** */
	public int getPort() {
		return port;
	}
	
}
