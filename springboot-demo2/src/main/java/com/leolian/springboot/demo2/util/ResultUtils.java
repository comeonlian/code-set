package com.leolian.springboot.demo2.util;

import com.leolian.springboot.demo2.entity.Result;

public class ResultUtils {
	/**
	 * 请求成功
	 * @param obj
	 * @return
	 */
	public static Result<Object> success(Object obj){
		Result<Object> res = new Result<>();
		res.setCode(0);
		res.setMsg("成功");
		res.setData(obj);
		return res;
	}
	
	/**
	 * @return
	 */
	public static Result<Object> success(){
		return success(null);
	}
	
	/**
	 * 发生异常
	 * @return
	 */
	public static Result<Object> error(Integer code, String msg){
		Result<Object> res = new Result<>();
		res.setCode(code);
		res.setMsg(msg);
		return res;
	}
	
}
