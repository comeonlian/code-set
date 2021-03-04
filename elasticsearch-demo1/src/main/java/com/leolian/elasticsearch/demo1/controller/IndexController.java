/**
 * 
 */
package com.leolian.elasticsearch.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 * @author lianliang
 * @date 2017年12月2日 下午11:26:15
 */
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
}
