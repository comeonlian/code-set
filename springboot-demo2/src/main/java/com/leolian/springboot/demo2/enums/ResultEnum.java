/**
 * 
 */
package com.leolian.springboot.demo2.enums;

/**
 * Description:
 * @author lianliang
 * @date 2017年11月20日 下午10:24:04
 */
public enum ResultEnum {
	SUCCESS(0, "成功"),
	UNKNOW(-1, "未知异常"),
	PRIMARY_SCHOOL(100, "你可能在上小学"),
	MIDDLE_SCHOOL(101, "你可能在上初中"),
	;
	
	private Integer code;
	private String msg;
	
	private ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
