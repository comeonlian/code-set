package com.leolian.code.fragment.book.javaweb.chapter03;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @Description: I/O流编码问题
 * @author lianliang
 * @date 2017年6月16日 上午9:42:00
 */
public class Demo01 {
	
	public static void main(String[] args) throws Exception {
		String file = "e:/stream.txt";
		String charset = "UTF-8";
		
		FileOutputStream outputStream = new FileOutputStream(file);
		OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
		
		try {
			writer.write("这是要保存的中文字符");
		} finally {
			writer.close();
		}
		
		FileInputStream inputStream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(inputStream, charset);
		
		StringBuffer sb = new StringBuffer();
		char[] buf = new char[64];
		int count = 0;
		try {
			while((count=reader.read(buf))!=-1) {
				sb.append(buf, 0, count);
			}
		} finally {
			reader.close();
		}
		
		System.out.println(sb.toString());
	}
	
}
