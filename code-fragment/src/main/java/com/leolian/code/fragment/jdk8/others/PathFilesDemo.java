/**
 * 
 */
package com.leolian.code.fragment.jdk8.others;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月25日 上午11:23:43
 */
public class PathFilesDemo {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		Path path1 = Paths.get("D:/Temp/Demo", "abc.txt");
		Path path2 = Paths.get("D:/Temp/Demo/abc.txt");
		URI u = URI.create("file:///D:/Temp/Demo/abc.txt");
		Path pathURI = Paths.get(u);

		Path filePath = FileSystems.getDefault().getPath("D:/Temp/Demo", "abc.txt");

		File file = new File("D:/Temp/Demo/abc.txt");
		Path p1 = file.toPath();
		p1.toFile();
		file.toURI();

		byte[] data = Files.readAllBytes(Paths.get("D:/Temp/Demo/abc.txt"));
		String content = new String(data, StandardCharsets.UTF_8);
		System.out.println(content);

		List<String> lines = Files.readAllLines(Paths.get("D:/Temp/Demo/abc.txt"));
		System.out.println(lines);

		Files.write(Paths.get("D:/Temp/Demo/abc.txt"), ("File Util Operator"+System.getProperty("line.separator")).getBytes(), StandardOpenOption.APPEND);
		
		/*
		InputStream ins = Files.newInputStream(filePath);
		OutputStream ops = Files.newOutputStream(filePath);
		BufferedReader reader = Files.newBufferedReader(filePath);
		BufferedWriter writer = Files.newBufferedWriter(filePath);
		*/
		
	}

}
