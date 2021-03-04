/**
 * 
 */
package com.leolian.springboot.demo3.classloader;

/**
 * Description:
 * @author lianliang
 * @date 2017年11月25日 下午4:48:59
 */
public class MsgHandler implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true) {
			BaseManager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
			manager.logic();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
