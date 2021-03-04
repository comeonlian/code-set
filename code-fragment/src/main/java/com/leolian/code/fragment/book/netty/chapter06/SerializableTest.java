package com.leolian.code.fragment.book.netty.chapter06;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class SerializableTest {
	/**
	 * 编码大小比较
	 * @throws IOException
	 */
	public static void size() throws IOException {
		UserInfo info = new UserInfo();
		info.buildUserId(100).buildUserName("Welcome to Netty");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		os.writeObject(info);
		os.flush();
		os.close();
		byte[] b = bos.toByteArray();
		System.out.println("The jdk serializable length is: "+b.length);
		bos.close();
		System.out.println("-----------------------------------------");
		System.out.println("The byte array serializable length is: "+info.codeC().length);
	}

	public static void main(String[] args) throws Exception{
		//size();
		perform();
	}
	
	/**
	 * 编码性能比较
	 * @throws IOException
	 */
	public static void perform() throws IOException {
		UserInfo info = new UserInfo();
		info.buildUserId(100).buildUserName("Welcome to Netty");
		int loop = 100000;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		long startTime = System.currentTimeMillis();
		for(int i=0; i<loop; i++){
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(info);
			os.flush();
			os.close();
			byte[] b = bos.toByteArray();
			bos.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("The jdk serializable cost time is: "+(endTime-startTime)+"ms");
		
		System.out.println("-------------------------------------");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		startTime = System.currentTimeMillis();
		for(int i=0; i<loop; i++){
			byte[] b = info.codeC(buffer);
		}
		endTime = System.currentTimeMillis();
		System.out.println("The byte array serializable cost time is: "+(endTime-startTime)+"ms");
		
	}
}
