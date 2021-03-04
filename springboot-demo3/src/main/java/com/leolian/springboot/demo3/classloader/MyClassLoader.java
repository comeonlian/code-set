/**
 * 
 */
package com.leolian.springboot.demo3.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Description:
 * @author lianliang
 * @date 2017年11月22日 下午10:09:06
 */
public class MyClassLoader extends ClassLoader {
	
	private String classpath;
	
	public MyClassLoader(String classpath) {
		super(ClassLoader.getSystemClassLoader());
		this.classpath = classpath;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = loadClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}

	/**
	 * @param name
	 * @return
	 */
	private byte[] loadClassData(String name) {
		FileInputStream fis = null;
		try{
			name = name.replace(".", "//");
			fis = new FileInputStream(new File(classpath+name+".class"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int b = 0;
			while((b=fis.read())!=-1){
				baos.write(b);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null!=fis)
					fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
