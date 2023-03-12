package com.wbw.apipassenger.service;

import com.wbw.internalcommon.dto.PassengerUser;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.dto.TokenResult;
import com.wbw.internalcommon.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {


    public ResponseResult getUserByAccessToken(String accessToken) {
        // 解析accessToken，拿到手机号
        log.info("accessToken: {}", accessToken);

        TokenResult tokenResult = JwtUtils.parseToken(accessToken);
        String phone = tokenResult.getPhone();

        // 根据手机号查询用户信息
        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("张三");
        passengerUser.setProfilePhoto("头像");

        return ResponseResult.success(passengerUser);
    }
}
