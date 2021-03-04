package com.leolian.code.fragment.book.jvm.chapter02;

/**
 * VM args: -Xss128k
 * Description: 
 * @author lianliang
 * @date 2018年3月28日 下午3:17:13
 */
public class JavaVMStackSOF {
	private int stackLength = 1;

	public void stackLeak() {
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length: " + oom.stackLength);
			throw e;
		}
	}

}