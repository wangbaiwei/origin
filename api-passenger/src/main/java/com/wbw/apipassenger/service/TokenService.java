package com.wbw.apipassenger.service;

import com.wbw.internalcommon.response.TokenResponse;
import com.wbw.internalcommon.constant.CommonStatusEnum;
import com.wbw.internalcommon.constant.TokenConstans;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.dto.TokenResult;
import com.wbw.internalcommon.utils.JwtUtils;
import com.wbw.internalcommon.utils.RedisPrefixUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public ResponseResult refreshToken(String refreshTokenSrc) {
        // 解析refreshToken

        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (tokenResult == null) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }

        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();

        // 读取redis中的refreshToken
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstans.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        // 校验refreshToken
        if (StringUtils.isBlank(refreshTokenRedis) || !refreshTokenSrc.trim().equals(refreshTokenRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }

        // 生成双token
        String refreshToken = JwtUtils.generatorToken(phone, identity, TokenConstans.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(phone, identity, TokenConstans.ACCESS_TOKEN_TYPE);
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstans.ACCESS_TOKEN_TYPE);

        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);
        return ResponseResult.success(tokenResponse);

    }
}
