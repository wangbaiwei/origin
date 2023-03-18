package com.wbw.apidriver.controller;

import com.wbw.apidriver.service.VerificationCodeService;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.VerificationCodeDTD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTD verificationCodeDTD) {

        log.info("司机的号码：{}", verificationCodeDTD.getDriverPhone());
        String driverPhone = verificationCodeDTD.getDriverPhone();
        return verificationCodeService.checkAndSendVerificationCode(driverPhone);


    }



}
