package com.wbw.servcedirveruser.controller;


import com.wbw.internalcommon.dto.DriverUser;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servcedirveruser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService driverUserService;


    @PostMapping("/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser) {
        log.info("driver user info: {}", driverUser);

        ResponseResult responseResult = driverUserService.addDriverUser(driverUser);
        return responseResult;
    }
}
