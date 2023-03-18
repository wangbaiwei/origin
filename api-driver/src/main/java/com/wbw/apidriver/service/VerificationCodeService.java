package com.wbw.apidriver.service;

import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    public ResponseResult checkAndSendVerificationCode(String driverPhone) {

        // 查询service-driver-user，该手机号的司机是否存在

        // 获取验证码

        // 调用第三方发生验证码

        // 存入redis

        return ResponseResult.success();
    }
}
