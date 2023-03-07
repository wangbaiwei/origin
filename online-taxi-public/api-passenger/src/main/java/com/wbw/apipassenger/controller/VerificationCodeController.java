package com.wbw.apipassenger.controller;

import com.wbw.apipassenger.request.VerificationCodeDTD;
import com.wbw.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTD verificationCodeDTD) {
        String passengerPhone = verificationCodeDTD.getPassengerPhone();
        System.out.println("接受到的手机号：" + passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }
}
