/**
 * 
 */
package com.leolian.springboot.demo3.classloader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * @author lianliang
 * @date 2017年11月25日 下午4:26:38
 */
public class ManagerFactory {
	private static final Map<String, LoadInfo> LOAD_MAP = new HashMap<>();
	
	private static final String CLASS_PATH = "D:/Java/Project/project-2/springboot-demo3/target/classes/";
	
	public static final String MY_MANAGER = "com.leolian.springboot.demo3.classloader.MyManager";
	
	public static BaseManager getManager(String className) {
		File loadFile = new File(CLASS_PATH + className.replace("\\.", "/") + ".class");
		long lastModified = loadFile.lastModified();
		if(LOAD_MAP.get(className)==null) {
			load(className, lastModified);
		} else if(LOAD_MAP.get(className).getLoadTime()!=lastModified) {
			load(className, lastModified);
		}
		return LOAD_MAP.get(className).getManager();
	}

	/**
	 * @param className
	 * @param lastModified
	 */
	private static void load(String className, long lastModified) {
		MyClassLoader classLoader = new MyClassLoader(CLASS_PATH);
		Class<?> clazz = null;
		BaseManager manager = null;
		try {
			clazz = classLoader.findClass(className);
			manager = newInstance(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoadInfo loadInfo = new LoadInfo(classLoader, lastModified);
		loadInfo.setManager(manager);
		LOAD_MAP.put(className, loadInfo);
	}

	/**
	 * @param clazz
	 * @return
	 * @throws Exception 
	 */
	private static BaseManager newInstance(Class<?> clazz) throws Exception {
		return (BaseManager) clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
	}
	
}
