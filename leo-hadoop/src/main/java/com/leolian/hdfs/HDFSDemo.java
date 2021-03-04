package com.leolian.hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class HDFSDemo {
	
	private FileSystem fileSystem;
	
	@Before
	public void initFileSystem() throws Exception{
		System.setProperty("hadoop.home.dir", "E:\\Java-Dev\\hadoop\\hadoop-2.7.3");
		fileSystem = FileSystem.get(new URI("hdfs://192.168.1.105:9000"), new Configuration());
	}
	
	@Test
	public void testMkdir() throws Exception{
		boolean result = fileSystem.mkdirs(new Path("/lian"));
		System.out.println(result);
	}
	
	@Test
	public void testUpload() throws Exception{
		OutputStream out = fileSystem.create(new Path("/lian/words.txt"));
		InputStream in = new FileInputStream(new File("E:\\wrods.txt"));
		IOUtils.copyBytes(in, out, 1024, true);
	}
	
	public static void main(String[] args) throws Exception {
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.105:9000"), new Configuration());
		InputStream in = fileSystem.open(new Path("/lian/words.txt"));
		OutputStream out = new FileOutputStream(new File("E:/hehe.txt"));
		IOUtils.copyBytes(in, out, 1024, true);
	}

}
