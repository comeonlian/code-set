/**
 * 
 */
package com.leolian.code.fragment.jdk8.others;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import sun.misc.Unsafe;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月25日 上午11:14:26
 */
public class TryWithResourceDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 自动关闭资源
		// 捕获多异常
		try (InputStream ins = new FileInputStream("D:\\Temp\\Demo\\abc.txt")) {
			byte[] bytes = new byte[1024];
			ins.read(bytes);
			TimeUnit.SECONDS.sleep(1);
			System.out.println(new String(bytes));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		// 处理反射异常
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			Unsafe unsafe = (Unsafe) f.get(null);
			System.out.println(unsafe.addressSize());
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

}
