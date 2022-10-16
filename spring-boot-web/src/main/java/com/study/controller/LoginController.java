package com.study.controller;

import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.study.listener.MySessionListener;

@RestController
public class LoginController {
	
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute("hello", new Random().nextInt());
		return "login sucessfully";
		
	}
	
	@RequestMapping("/online")
	public String login() {
		return "当前登录人数为： " + MySessionListener.count;
		
	}
}
