package com.leolian.code.fragment.book.jvm.chapter03;

/**
 * 结果与书本描述一致
 * Description: 
 * @author lianliang
 * @date 2018年3月29日 下午6:02:48
 */
public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;

	public void isAlive() {
		System.out.println("yes, i am still alive");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}

	public static void main(String[] args) throws Exception {
		SAVE_HOOK = new FinalizeEscapeGC();
		SAVE_HOOK = null;
		System.gc();
		
		Thread.sleep(500);
		if(SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no, i am dead :(");
		}
		
		SAVE_HOOK = null;
		
		System.gc();
		
		Thread.sleep(500);
		if(SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no, i am dead :(");
		}
	}

}