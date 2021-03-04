/**
 * 
 */
package com.leolian.code.fragment.book.concurrence.chapter05;

import java.util.Vector;

/**
 * Description:
 * @author lianliang
 * @date 2018年1月13日 下午9:08:47
 */
public class ReliableVector {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Vector<String> vector = new Vector<>();
		
		synchronized(vector) {
			for(int i=0; i<vector.size(); i++){
				//doSomething(vector.get(i));
			}
		}
	}

}
