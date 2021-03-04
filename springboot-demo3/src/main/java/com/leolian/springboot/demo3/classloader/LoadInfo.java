/**
 * 
 */
package com.leolian.springboot.demo3.classloader;

/**
 * Description:
 * @author lianliang
 * @date 2017年11月25日 下午4:15:00
 */
public class LoadInfo {
	private MyClassLoader classloader;
	private long loadTime;
	
	private BaseManager manager;

	public LoadInfo(MyClassLoader classloader, long loadTime) {
		this.classloader = classloader;
		this.loadTime = loadTime;
	}

	public MyClassLoader getClassloader() {
		return classloader;
	}

	public void setClassloader(MyClassLoader classloader) {
		this.classloader = classloader;
	}

	public long getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}

	public BaseManager getManager() {
		return manager;
	}

	public void setManager(BaseManager manager) {
		this.manager = manager;
	}
	
}
