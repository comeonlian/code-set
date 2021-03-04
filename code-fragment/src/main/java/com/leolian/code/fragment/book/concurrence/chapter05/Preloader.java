package com.leolian.code.fragment.book.concurrence.chapter05;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description: FutureTask实现一个闭锁
 * @author lianliang
 * @date 2018年1月15日 上午10:27:48
 */
public class Preloader {
	private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
		public ProductInfo call() throws DataLoadException {
			return loadProductInfo();
		}
		
	});
	
	private ProductInfo loadProductInfo() {
		return null;
	}
	
	
	private final Thread thread = new Thread(future);

	public void start() {
		thread.start();
	}

	public ProductInfo get() throws Exception {
		try {
			return future.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause instanceof DataLoadException)
				throw (DataLoadException) cause;
			else
				throw launderThrowable(cause);
		}
	}

	public static RuntimeException launderThrowable(Throwable t) {
		if(t instanceof RuntimeException)
			return (RuntimeException) t;
		else if (t instanceof Error)
			throw (Error) t;
		else
			throw new IllegalArgumentException("Not unchecked", t);
	}

}
/**
 * Description: 
 * @author lianliang
 * @date 2018年1月15日 上午10:11:53
 */
class ProductInfo {
	
}

class DataLoadException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8331012417796798458L;
	
}
