package com.leolian.springboot.demo2.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leolian.springboot.demo2.entity.Student;
import com.leolian.springboot.demo2.enums.ResultEnum;
import com.leolian.springboot.demo2.exception.StudentException;
import com.leolian.springboot.demo2.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	/**
	 * 事务处理
	 */
	@Transactional
	public void insertStudents(){
		Student stu1 = new Student();
		stu1.setName("Hehe");
		stu1.setAge(35);
		repository.save(stu1);
		
		Student stu2 = new Student();
		stu2.setName("Heihei");
		stu2.setAge(38);
		repository.save(stu2);
	}
	
	/**
	 * 统一异常处理
	 * @param id
	 */
	public void getAge(Integer id) throws Exception{
		Student stu = repository.getOne(id);
		if(stu.getAge()<10){
			throw new StudentException(ResultEnum.PRIMARY_SCHOOL);
		}else if(stu.getAge()>10 && stu.getAge()<16){
			throw new StudentException(ResultEnum.MIDDLE_SCHOOL);
		}
	}
	
	
	public Student findOne(Integer id){
		return repository.findOne(id);
	}
}
