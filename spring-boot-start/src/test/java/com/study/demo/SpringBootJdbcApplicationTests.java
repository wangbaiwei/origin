package com.study.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.demo.bean.Person;

@SpringBootTest
class SpringBootJdbcApplicationTests {
	
	@Autowired
	public Person person;

	@Test
	void contextLoads() {
		System.out.println(person.getAge());
	}

}
