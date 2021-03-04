package com.leolian.springboot.demo1.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leolian.springboot.demo1.dao.StudentRepository;
import com.leolian.springboot.demo1.entity.Student;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	@Transactional
	public void insertStudents(){
		Student stu1 = new Student();
		stu1.setName("Hehe");
		stu1.setAge(35);
		repository.save(stu1);
		
		Student stu2 = new Student();
		stu2.setName("Heihei");
		stu2.setAge(38);
		int i = 1/0;
		repository.save(stu2);
	}
	
}
