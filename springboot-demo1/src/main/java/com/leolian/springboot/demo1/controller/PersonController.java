package com.leolian.springboot.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leolian.springboot.demo1.entity.Person;

@RestController
public class PersonController {
	
	@Autowired
	private Person person;

	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public String getPerson(){
		return person.toString();
	}
	
}
