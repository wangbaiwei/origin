package com.wbw.servicepassengeruser.controller;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.VerificationCodeDTD;
import com.wbw.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTD verificationCodeDTD) {
        System.out.println("user service ");
        String passengerPhone = verificationCodeDTD.getPassengerPhone();
        System.out.println("乘客的手机号：" + passengerPhone);
        return ResponseResult.success();
    }


}
