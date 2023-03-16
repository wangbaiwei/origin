package com.wbw.servcedirveruser.controller;


import com.wbw.internalcommon.dto.DriverUser;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servcedirveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private DriverUserService driverUserService;


    @PostMapping("/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser) {

        ResponseResult responseResult = driverUserService.addDriverUser(driverUser);
        return responseResult;
    }
}
