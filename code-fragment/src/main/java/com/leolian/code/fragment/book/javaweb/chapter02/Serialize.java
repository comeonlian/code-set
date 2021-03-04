package com.leolian.code.fragment.book.javaweb.chapter02;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @Description: Java序列化技术
 * @author lianliang
 * @date 2017年6月14日 上午9:54:55
 */
public class Serialize implements Serializable{
	private static final long serialVersionUID = 8081407865153293768L;
	
	public int num = 1390;
	
	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("d:/Temp/serialize.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			Serialize serialize = new Serialize();
			oos.writeObject(serialize);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
