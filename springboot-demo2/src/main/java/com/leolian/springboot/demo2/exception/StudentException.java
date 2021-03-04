package com.leolian.springboot.demo2.exception;

import com.leolian.springboot.demo2.enums.ResultEnum;

/**
 * Description:
 * @author lianliang
 * @date 2017年11月20日 下午10:08:19
 */
public class StudentException extends Exception{
	
	private static final long serialVersionUID = 227011688407755588L;
	
	private Integer code;

	public StudentException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}

	public Integer getCode() {
		return code;
	}
	
}
