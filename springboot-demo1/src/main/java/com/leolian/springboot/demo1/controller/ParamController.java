package com.leolian.springboot.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/param")
public class ParamController {
	
	@RequestMapping(value = "/module/{id}", method = RequestMethod.GET)
	public String getParam(@PathVariable("id") Integer id){
		return "Id: "+id;
	}
	
	@GetMapping("/modl")
	public String getRequestParam(@RequestParam("id") Integer myId){
		return "Id: "+myId;
	}
	
	
}
