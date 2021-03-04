package com.leolian.springboot.demo1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Value("${cupSize}")
	private String cupSize;
	
	@Value("${age}")
	private Integer age;
	
	@Value("${content}")
	private String content;
	
	@RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
	public String say(){
		return "Hello SpringBoot!";
	}
	
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public String config(){
		return content;
	}
	
}
