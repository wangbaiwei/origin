package com.wbw.apipassenger.controller;

import com.wbw.apipassenger.service.UserService;
import com.wbw.internalcommon.dto.PassengerUser;
import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request) {
        // 从http请求中获取 accessToken
        String authorization = request.getHeader("Authorization");


        // 根据accessToken 查询用户信息


        ResponseResult userByAccessToken = userService.getUserByAccessToken(authorization);
        return userByAccessToken;

    }
}
