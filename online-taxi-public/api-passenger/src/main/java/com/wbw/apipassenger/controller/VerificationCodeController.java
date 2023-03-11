package com.wbw.apipassenger.controller;

import com.wbw.apipassenger.request.VerificationCodeDTD;
import com.wbw.apipassenger.service.VerificationCodeService;
import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTD verificationCodeDTD) {
        String passengerPhone = verificationCodeDTD.getPassengerPhone();
        System.out.println("接受到的手机号：" + passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }


    @PostMapping("/verfication-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTD verificationCodeDTD) {
        String passengerPhone = verificationCodeDTD.getPassengerPhone();
        String verificationCode = verificationCodeDTD.getVerificationCode();

        System.out.println("手机号：" + passengerPhone + " 验证码：" + verificationCode);

        return verificationCodeService.checkCode(passengerPhone, verificationCode);

    }
}
