package com.wbw.apipassenger.service;

import com.wbw.apipassenger.remote.ServicePassengerUserClient;
import com.wbw.internalcommon.dto.PassengerUser;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.dto.TokenResult;
import com.wbw.internalcommon.request.VerificationCodeDTD;
import com.wbw.internalcommon.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;


    public ResponseResult getUserByAccessToken(String accessToken) {
        // 解析accessToken，拿到手机号
        log.info("accessToken: {}", accessToken);

        TokenResult tokenResult = JwtUtils.parseToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号：{}", phone);

        // 根据手机号查询用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);
        return ResponseResult.success(userByPhone.getData());
    }
}
