package com.wbw.apipassenger.service;

import com.wbw.apipassenger.remote.ServicePassengerUserClient;
import com.wbw.apipassenger.remote.ServiceVerificationcodeClient;
import com.wbw.internalcommon.response.TokenResponse;
import com.wbw.internalcommon.constant.CommonStatusEnum;
import com.wbw.internalcommon.constant.IdentityConstants;
import com.wbw.internalcommon.constant.TokenConstans;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.request.VerificationCodeDTD;
import com.wbw.internalcommon.response.NumberCodeResponse;
import com.wbw.internalcommon.utils.JwtUtils;
import com.wbw.internalcommon.utils.RedisPrefixUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public ResponseResult generatorCode(String passengerPhone) {

        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("remote number code: " + numberCode);

        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone, IdentityConstants.PASSENGER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到手机上，以阿里短信服务，腾讯短信通，华信，客联
        return ResponseResult.success("");

    }


    /**
     * 校验验证码
     *
     * @param passengerPhone   手机号
     * @param verificatoinCode 验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificatoinCode) {
        // 根据手机号到redis读取验证码
        System.out.println("根据手机号到redis读取验证码");
        // 生成key
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone, IdentityConstants.PASSENGER_IDENTITY);
        // 更具key获取value
        String codeReids = stringRedisTemplate.opsForValue().get(key);
        System.out.println(key); // passenger-verification-code-18669945566
        System.out.println("redis中的value: " + codeReids);
        // 校验验证码
        if (StringUtils.isBlank(codeReids)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        if (!verificatoinCode.trim().equals(codeReids.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        // 判断原来是否有用户，并进行对应的处理
        VerificationCodeDTD verificationCodeDTD = new VerificationCodeDTD();
        verificationCodeDTD.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTD);

        // 颁发令牌，不应该用魔法值，用常量
        String accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstans.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstans.REFRESH_TOKEN_TYPE);

        // 将token存入到redis中
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstans.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstans.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);


        // 响应token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);
        return ResponseResult.success(tokenResponse);
    }
}
