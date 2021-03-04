/**
 * 
 */
package com.leolian.springboot.demo2.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leolian.springboot.demo2.entity.Student;

/**
 * Description:
 * @author lianliang
 * @date 2017年11月20日 下午10:36:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
	
	@Autowired
	private StudentService service;
	
	@Test
	public void findOneTest(){
		Student student = service.findOne(6);
		Assert.assertNotNull(student);
	}
	
}
