package com.study.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener{
	
	public static volatile int count;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		count++;
		System.out.println("session created");
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		count--;
		System.out.println("session destroy");
	}

}
