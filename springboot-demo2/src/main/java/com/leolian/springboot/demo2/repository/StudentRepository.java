package com.leolian.springboot.demo2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leolian.springboot.demo2.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	public List<Student> findByAge(Integer age);
	
}