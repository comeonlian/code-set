/**
 * 
 */
package com.leolian.springboot.demo3.classloader;

/**
 * Description:
 * @author lianliang
 * @date 2017年11月25日 下午4:09:26
 */
public class MyManager implements BaseManager {

	/* (non-Javadoc)
	 * @see com.leolian.classloader.BaseManager#logic()
	 */
	public void logic() {
		System.out.println("my manager print. hehehe... heihei");
	}

}
