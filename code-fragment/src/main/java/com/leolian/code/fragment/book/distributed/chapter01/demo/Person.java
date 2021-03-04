package com.leolian.code.fragment.book.distributed.chapter01.demo;

import java.util.Date;

public class Person {
	private String name;
	private Date birth;
	private int age;
	private String address;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", birth=" + birth + ", age=" + age + ", address=" + address + "]";
	}
}
