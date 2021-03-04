package com.leolian.code.fragment.book.distributed.chapter03.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 获取文件头字节数，并判断文件类型
 * 
 * @Description:
 * @author lianliang
 * @date 2017年10月23日 上午10:29:45
 */
public class FileValidate {
	
	private static String getFileHeader(String filePath) throws IOException {
		byte[] b = new byte[28];
		InputStream input = null;
		input = new FileInputStream(filePath);
		input.read(b, 0, 28);
		input.close();
		return bytes2Hex(b);
	}

	private static String bytes2Hex(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			sb.append(String.format("%02x", b[i]));
		}
		return sb.toString();
	}

	public static FileType getType(String filePath) throws IOException {
		String fileHead = getFileHeader(filePath);
		if (fileHead == null || fileHead.length() == 0) {
			return null;
		}
		fileHead = fileHead.toUpperCase();
		FileType[] fileTypes = FileType.values();
		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}
		return null;
	}
}
