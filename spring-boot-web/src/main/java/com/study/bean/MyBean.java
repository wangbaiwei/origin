package com.study.bean;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.study.filter.MyFilter;
import com.study.listener.MySessionListener;
import com.study.servlet.MyServlet;

@Configuration
public class MyBean {
	
	@Bean
	 public ServletRegistrationBean<MyServlet> registerBean() {
		 ServletRegistrationBean<MyServlet> servlet = new ServletRegistrationBean<>(new MyServlet(), "/s2");
		 servlet.setLoadOnStartup(0);
		 return servlet;
	 }
	
	@Bean
	@Order(0)
	public FilterRegistrationBean<MyFilter> getFilterBean() {
		FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>(new MyFilter());
		return bean;
	}
	
	@Bean
	public ServletListenerRegistrationBean<MySessionListener> getListener() {
		ServletListenerRegistrationBean<MySessionListener> bean = new ServletListenerRegistrationBean<MySessionListener>(new MySessionListener());
		return bean;
		
	}
}
