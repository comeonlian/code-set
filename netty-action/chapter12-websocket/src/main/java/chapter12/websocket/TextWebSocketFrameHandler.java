package chapter12.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * 处理文本帧
 * @Description: 
 * @author lianliang
 * @date 2017年9月22日 上午11:29:56
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	private ChannelGroup group;
	
	public TextWebSocketFrameHandler(ChannelGroup group) {
		this.group = group;
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
			ctx.pipeline().remove(HttpRequestHandler.class);
			group.writeAndFlush(new TextWebSocketFrame("Client "+ctx.channel()+" joined"));
			group.add(ctx.channel());
		}else {
			super.userEventTriggered(ctx, evt);
		}
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		group.writeAndFlush(msg.retain());
	}

}
