package com.leolian.code.fragment.book.concurrence.chapter05;

/**
 * Description: 计算接口
 * @author lianliang
 * @date 2018年1月15日 下午4:40:58
 */
public interface Computable<A, V> {
	V compute(A arg) throws InterruptedException;
}