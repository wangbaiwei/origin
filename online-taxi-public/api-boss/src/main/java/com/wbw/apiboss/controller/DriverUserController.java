package com.wbw.apiboss.controller;

import com.wbw.apiboss.service.DriverUserService;
import com.wbw.internalcommon.dto.DriverUser;
import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;


    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser) {

        ResponseResult responseResult = driverUserService.addDriverUser(driverUser);
        return ResponseResult.success(responseResult);
    }
    @PutMapping("/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser) {

        ResponseResult responseResult = driverUserService.updateDriverUser(driverUser);
        return ResponseResult.success(responseResult);
    }
}
