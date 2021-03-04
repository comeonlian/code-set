package com.leolian.code.fragment.book.distributed.chapter03.encrypt;

import java.io.IOException;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 数字摘要算法实现
 * @Description: 
 * @author lianliang
 * @date 2017年10月24日 上午9:56:20
 */
public class DigitalDigest {
	
	public static byte[] testMD5(String content) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = md.digest(content.getBytes("utf8"));
		return bytes;
	}
	
	public static byte[] testSHA1(String content) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] bytes = md.digest(content.getBytes("utf8"));
		return bytes;
	}
	
	public static String bytes2Hex(byte[] bytes) {
		StringBuilder hex = new StringBuilder();
		for(int i=0; i<bytes.length; i++) {
			byte b = bytes[i];
			boolean negative = false; //
			if(b<0)
				negative = true;
			int inte = Math.abs(b);
			if(negative)
				inte = inte | 0x80;
			String temp = Integer.toHexString(inte & 0xFF);
			if(temp.length() == 1) {
				hex.append("0");
			}
			hex.append(temp.toLowerCase());
		}
		return hex.toString();
	}

	public static byte[] hex2Bytes(String hex) {
		byte[] bytes = new byte[hex.length()/2];
		for(int i=0; i<hex.length(); i=i+2) {
			String subStr = hex.substring(i, i+2);
			boolean negative = false;
			int inte = Integer.parseInt(subStr, 16);
			if(inte > 127)
				negative = true;
			if(inte==128) {
				inte = -128;
			}else if(negative){
				inte = 0 - (inte & 0x7F);
			}
			byte b = (byte)inte;
			bytes[i/2] = b;
		}
		return bytes;
	}
	
	public static String byte2Base64(byte[] bytes) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		return base64Encoder.encode(bytes);
	}

	public static byte[] base642byte(String base64) throws IOException {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		return base64Decoder.decodeBuffer(base64);
	}
	
}
