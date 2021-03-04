package com.leolian.code.fragment.book.nettyaction.chapter11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Description: 自动聚合HTTP 的消息片段
 * @author lianliang
 * @date 2017年9月21日 上午10:48:52
 */
public class HttpAggregatorInitializer extends ChannelInitializer<Channel> {
	private final boolean isClient;

	public HttpAggregatorInitializer(boolean isClient) {
		this.isClient = isClient;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if (isClient) {
			pipeline.addLast("codec", new HttpClientCodec());
		} else {
			pipeline.addLast("codec", new HttpServerCodec());
		}
		pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));
	}
}