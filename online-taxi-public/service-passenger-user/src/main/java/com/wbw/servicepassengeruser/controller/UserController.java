package com.wbw.servicepassengeruser.controller;

import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.VerificationCodeDTD;
import com.wbw.servicepassengeruser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTD verificationCodeDTD) {
        System.out.println("user service ");
        String passengerPhone = verificationCodeDTD.getPassengerPhone();
        System.out.println("乘客的手机号：" + passengerPhone);
        ResponseResult responseResult = userService.loginOrRegister(passengerPhone);
        return responseResult;
    }

    @GetMapping("/user/{phone}")
    public ResponseResult geUser(@PathVariable("phone") String passengerPhone) {
        log.info("service-passenger-user get user by phone, phone: {}", passengerPhone);
        return userService.getUserByPhone(passengerPhone);
    }


}
