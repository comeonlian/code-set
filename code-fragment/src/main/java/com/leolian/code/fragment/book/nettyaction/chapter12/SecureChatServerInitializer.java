package com.leolian.code.fragment.book.nettyaction.chapter12;

import javax.net.ssl.SSLEngine;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

/**
 * 初始化ChannelPipeline
 * @Description: 
 * @author lianliang
 * @date 2017年9月22日 上午11:31:33
 */
public class SecureChatServerInitializer extends ChatServerInitializer {
	private SslContext sslContext;
	
	public SecureChatServerInitializer(ChannelGroup group, SslContext context) {
		super(group);
		this.sslContext = context;
	}
	
	@Override
	protected void initChannel(Channel ch) throws Exception {
		super.initChannel(ch);
		SSLEngine sslEngine = sslContext.newEngine(ch.alloc());
		sslEngine.setUseClientMode(false);
		ch.pipeline().addFirst(new SslHandler(sslEngine));
	}

}
