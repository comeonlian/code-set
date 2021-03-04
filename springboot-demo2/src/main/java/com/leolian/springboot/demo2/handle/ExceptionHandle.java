package com.leolian.springboot.demo2.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leolian.springboot.demo2.entity.Result;
import com.leolian.springboot.demo2.enums.ResultEnum;
import com.leolian.springboot.demo2.exception.StudentException;
import com.leolian.springboot.demo2.util.ResultUtils;

/**
 * Description: 统一异常处理
 * @author lianliang
 * @date 2017年11月20日 下午9:52:07
 */
@ControllerAdvice
public class ExceptionHandle {
	
	private static final Logger log = LoggerFactory.getLogger(ExceptionHandle.class);
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Result<Object> handle(Exception e){
		if(e instanceof StudentException){
			StudentException ex = (StudentException) e;
			return ResultUtils.error(ex.getCode(), ex.getMessage());
		}else{
			log.error("[系统异常]: {}", e);
			return ResultUtils.error(ResultEnum.UNKNOW.getCode(), ResultEnum.UNKNOW.getMsg());
		}
	}
	
}
