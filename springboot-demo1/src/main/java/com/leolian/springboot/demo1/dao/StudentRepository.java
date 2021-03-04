package com.leolian.springboot.demo1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leolian.springboot.demo1.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	public List<Student> findByAge(Integer age);
	
}
