package com.leolian.code.fragment.book.netty.chapter08;

import com.google.protobuf.InvalidProtocolBufferException;
import com.leolian.code.fragment.book.netty.chapter08.SubscribeReqProto.SubscribeReq;

/**
 * @Description: 测试protobuf
 * @author lianliang
 * @date 2017年7月17日 上午11:36:35
 */
public class TestSubscribeReqProto {
	
	public static byte[] encode (SubscribeReqProto.SubscribeReq req) {
		return req.toByteArray();
	}
	
	public static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
		return SubscribeReqProto.SubscribeReq.parseFrom(body);
	}
	
	public static SubscribeReqProto.SubscribeReq createSubscribeReq(){
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqId(1);
		builder.setUserName("LianLiang");
		builder.setProductName("Netty Book");
		builder.setAddress("Shenzhen nanshang");
		return builder.build();
	}
	
	public static void main(String[] args) throws InvalidProtocolBufferException {
		SubscribeReq req = createSubscribeReq();
		System.out.println("Before encode: "+req.toString());
		SubscribeReq req2 = decode(encode(req));
		System.out.println("After decode: "+req.toString());
		System.out.println("Assert equal: --> "+req.equals(req2));
	}
	
}