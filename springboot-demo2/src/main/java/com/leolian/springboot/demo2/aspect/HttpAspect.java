package com.leolian.springboot.demo2.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * AOP拦截，记录HTTP请求
 * @author Lian
 */
@Aspect
@Component
public class HttpAspect {
	private static final Logger log = LoggerFactory.getLogger(HttpAspect.class);
	
	
	@Pointcut("execution(public * com.leolian.springboot.demo2.controller.StudentController.*(..))")
	public void log() {
		
	}
	
	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		// log.info("StudentController方法 before拦截");
		
		// 记录HTTP请求
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// url
		log.info("url={}", request.getRequestURL());
		// method
		log.info("method={}", request.getMethod());
		// ip
		log.info("ip={}", request.getRemoteAddr());
		// 类方法
		log.info("classMethod={}", joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
		// 参数
		log.info("args={}", joinPoint.getArgs());
		
	}
	
	@After("log()")
	public void doAfter() {
		log.info("StudentController方法 after拦截");
	}
	
	@AfterReturning(returning="obj", value="log()")
	public void doAfterReturning(Object obj) {
		log.info("response={}", obj.toString());
	}
	
}
