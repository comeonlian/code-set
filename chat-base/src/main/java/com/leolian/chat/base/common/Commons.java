package com.leolian.chat.base.common;

import java.nio.charset.Charset;

public interface Commons {
	// 端口
	int PORT = 9898;
	
	// 自定义协议格式
	String USER_EXIST = "system message: user exist, please change a name";
	String USER_CONTENT_SPILIT = "#@#";
	
	// 字符集
	Charset CHARSET = Charset.forName("UTF-8");
}
