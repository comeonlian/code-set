package com.leolian.code.fragment.book.jvm.chapter10.annotation;

/**
 * javac  -encoding  utf-8 NameChecker.java NameCheckProcessor.java
 * 
 * javac -processor NameCheckProcessor BADLY_NAMED_CODE.java
 * 
 * @description: 
 * @author lianliang
 * @date 2018年9月19日 下午1:48:51
 */
public class BADLY_NAMED_CODE {

	enum colors {
		red, blue, green;
	}

	static final int _FORTY_TWO = 42;

	public static int NOT_A_CONSTANT = _FORTY_TWO;

	protected void BADLY_NAMED_CODE() {
		return;
	}

	public void NotCamelCASEmethodNAME() {
		return;
	}
}