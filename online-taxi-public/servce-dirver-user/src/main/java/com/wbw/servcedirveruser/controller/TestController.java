package com.wbw.servcedirveruser.controller;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.servcedirveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DriverUserService driverUserService;

    @GetMapping("/test")
    public String test() {
        return "servce-dirver-user";
    }

    @GetMapping("/test-db")
    public ResponseResult testDb() {
        return driverUserService.testGetDirverUser();
    }

}
