package com.study.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	
	@RequestMapping("/hello")
	public String sayHello() {
		return "hello";
	}
	
	public List<Map<String,Object>> queryInfo() {
		return null;
	}

}
