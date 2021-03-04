/**
 * 
 */
package com.leolian.springboot.demo3.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description:
 * @author lianliang
 * @date 2017年11月25日 下午5:41:27
 */
@Controller
@RequestMapping("/start")
public class HelloWorldController {
	
	
	@RequestMapping(value="/helloworld", method=RequestMethod.GET)
	public String helloWorld(HttpServletRequest request) {
		request.setAttribute("val", "SpringBoot Hello World! Hehehe ...");
		return "hello";
	}
	
}
