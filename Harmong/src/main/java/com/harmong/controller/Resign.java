package com.harmong.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.harmong.util.CommonUtil;
import com.harmong.util.DbUtil;


@WebServlet("/resign")
public class Resign extends HttpServlet{

	/**
	 *  resign
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 注册成功表示
		boolean resignFlag = true;
		String name = CommonUtil.trim(request.getParameter("name"));
		String password = CommonUtil.trim(request.getParameter("password"));
		
		// 呢称非空
		if (CommonUtil.isNullOrEmpty(name)) {
			resignFlag = false;
		}
		// 密码非空
		if (CommonUtil.isNullOrEmpty(password)) {
			resignFlag = false;
		}
		
		// 注册信息
		String sql = "insert into user(user_id, user_name, user_idcard, user_password, user_phone, user_resign_datetime) value(?,?,?,?,?,?)";
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", CommonUtil.generateUserId());
		paraMap.put("userName", name);
		paraMap.put("userIdcard", "");
		paraMap.put("userPassword", password);
		paraMap.put("userPhone", "");
		paraMap.put("userResignDatetime", new Date());
		
		try {
			ServletOutputStream out = response.getOutputStream();
			int res = new DbUtil().update(sql, new Object[] {CommonUtil.generateUserId(), name, "", password, "", new Date()});
			if (resignFlag && res > 0) { // 注册成功
				out.write("true".getBytes());
				out.flush();
			} else { // 注册失败
				out.write("false".getBytes());
				out.flush();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
