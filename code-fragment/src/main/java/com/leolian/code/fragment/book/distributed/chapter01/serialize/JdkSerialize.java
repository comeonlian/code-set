package com.leolian.code.fragment.book.distributed.chapter01.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * @Description: JDK自带的序列化
 * @author lianliang
 * @date 2017年9月26日 下午2:47:10
 */
public class JdkSerialize {
	
	public static void main(String[] args) throws Exception{
		Person zhansan = new Person("Lilei", 25);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(os);
		out.writeObject(zhansan);
		byte[] zhansanBytes = os.toByteArray();
		
		System.out.println("Object array: "+Arrays.toString(zhansanBytes));
		
		ByteArrayInputStream is = new ByteArrayInputStream(zhansanBytes);
		ObjectInputStream in = new ObjectInputStream(is);
		Person person = (Person) in.readObject();
		System.out.println(person);
	}
	
}
