package com.leolian.springboot.demo2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leolian.springboot.demo2.entity.Result;
import com.leolian.springboot.demo2.entity.Student;
import com.leolian.springboot.demo2.repository.StudentRepository;
import com.leolian.springboot.demo2.service.StudentService;
import com.leolian.springboot.demo2.util.ResultUtils;

@RestController
public class StudentController {
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private StudentService service;
	
	@GetMapping(value = "/students")
	public List<Student> getAll(){
		return repository.findAll();
	}
	
	/**
	 * 接受一个bean，并校验字段值
	 * @param student
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/students")
	public Result<Object> addStudent(@Valid Student student, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return ResultUtils.error(1, bindingResult.getFieldError().getDefaultMessage());
		}
		return ResultUtils.success(repository.save(student));
	}
	
	@GetMapping("/student/{id}")
	public Student getOne(@PathVariable("id") Integer id){
		return repository.findOne(id);
	}
	
	@PutMapping("/student/{id}")
	public Student updateOne(@PathVariable("id") Integer id,
						@RequestParam(value="name", required=true) String name,
						@RequestParam(value="age", required=false, defaultValue="20") Integer age){
		Student stu = new Student();
		stu.setId(id);
		stu.setName(name);
		stu.setAge(age);
		return repository.save(stu);
	}
	
	@DeleteMapping("/student/{id}")
	public void delOne(@PathVariable("id") Integer id){
		repository.delete(id);
	}
	
	@GetMapping("/student/age/{age}")
	public List<Student> findByAge(@PathVariable("age") Integer age){
		return repository.findByAge(age);
	}
	
	@GetMapping("/addStudents")
	public void addStudents(){
		service.insertStudents();
	}
	
	@GetMapping("/student/getAge/{id}")
	public void getAge(@PathVariable("id")Integer id) throws Exception{
		service.getAge(id);
	}
	
}
