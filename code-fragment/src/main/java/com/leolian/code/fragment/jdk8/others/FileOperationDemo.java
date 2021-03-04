/**
 * 
 */
package com.leolian.code.fragment.jdk8.others;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 * 
 * @author lianliang
 * @date 2018年4月25日 上午11:46:27
 */
public class FileOperationDemo {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
//		Path path = Paths.get("D:/Temp/Demo/aaa.txt");
//		if (!Files.exists(path)) {
//			Files.createFile(path);
//			// Files.createDirectory(path);
//		}
//		
//		Path path1 = Paths.get("D:/Temp/Demo");
//		Files.createTempFile(path1, null, ".tmp");
//		
//		Files.createTempFile(null, null);
//		Files.createTempDirectory(path1, null);
//		Files.createTempDirectory(null);
		
		Path path2 = Paths.get("D:/Temp/Demo");
		//Files.list(path2).forEach(System.out::println);
		
		// Files.walk(path2).forEach(System.out::println);
		
		Path source = Paths.get("D:/Temp/Demo/abc.txt");
		Path target = Paths.get("D:/Temp/Demo/xxxx.txt");
		// Files.copy(source, target);
		
		Path target2 = Paths.get("D:/Temp/Demo/sub/xxxx.txt");
		//Files.move(target, target2);
		
		Path source1 = Paths.get("D:/Temp/Demo/7115147201691691458.tmp");
		//Files.delete(source1);
		
		// a,b,c
		String[] arr = new String[] {"hehe", "heihei", "haha", "hihi"};
		String str = String.join(",", arr);
		System.out.println(str);
		
		
	}

}
