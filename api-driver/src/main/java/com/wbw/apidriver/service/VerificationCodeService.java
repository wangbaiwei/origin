package com.wbw.apidriver.service;

import com.wbw.apidriver.remote.ServiceDriverUserClient;
import com.wbw.apidriver.remote.ServiceVerficationCodeClient;
import com.wbw.internalcommon.constant.CommonStatusEnum;
import com.wbw.internalcommon.constant.DriverCarConstants;
import com.wbw.internalcommon.constant.IdentityConstants;
import com.wbw.internalcommon.constant.TokenConstans;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.response.DriverUserExistsResponse;
import com.wbw.internalcommon.response.NumberCodeResponse;
import com.wbw.internalcommon.response.TokenResponse;
import com.wbw.internalcommon.utils.JwtUtils;
import com.wbw.internalcommon.utils.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceVerficationCodeClient serviceVerficationCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult checkAndSendVerificationCode(String driverPhone) {

        // 查询service-driver-user，该手机号的司机是否存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriver(driverPhone);
        DriverUserExistsResponse data = driverUserExistsResponseResponseResult.getData();
        int isExists = data.getIsExists();
        if (isExists == DriverCarConstants.DRIVER_NOT_EXISTS) {
            log.info("{}对应的司机不存在", driverPhone);
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }
        log.info("{}对应的司机存在", driverPhone);


        // 获取验证码
        ResponseResult<NumberCodeResponse> verificationCode = serviceVerficationCodeClient.getVerificationCode(6);
        NumberCodeResponse numberCodeResponse = verificationCode.getData();
        int numberCode = numberCodeResponse.getNumberCode();
        log.info("验证码：{}", numberCode);

        // 调用第三方发生验证码，第三方，阿里短信服务，腾讯，华信，容联

        // 存入redis
        String key = RedisPrefixUtils.generatorKeyByPhone(driverPhone, IdentityConstants.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);
        return ResponseResult.success(data);
    }


    /**
     * 校验验证码
     *
     * @param driverPhone      手机号
     * @param verificatoinCode 验证码
     * @return
     */
    public ResponseResult checkCode(String driverPhone, String verificatoinCode) {
        // 根据手机号到redis读取验证码
        System.out.println("根据手机号到redis读取验证码");
        // 生成key
        String key = RedisPrefixUtils.generatorKeyByPhone(driverPhone, IdentityConstants.DRIVER_IDENTITY);
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

        // 颁发令牌，不应该用魔法值，用常量
        String accessToken = JwtUtils.generatorToken(driverPhone, IdentityConstants.DRIVER_IDENTITY, TokenConstans.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(driverPhone, IdentityConstants.DRIVER_IDENTITY, TokenConstans.REFRESH_TOKEN_TYPE);

        // 将token存入到redis中
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(driverPhone, IdentityConstants.DRIVER_IDENTITY, TokenConstans.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(driverPhone, IdentityConstants.DRIVER_IDENTITY, TokenConstans.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        // 响应token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);
        return ResponseResult.success(tokenResponse);
    }
}
