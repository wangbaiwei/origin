package com.wbw.apipassenger.controller;

import com.wbw.apipassenger.response.TokenResponse;
import com.wbw.apipassenger.service.TokenService;
import com.wbw.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {

        String refreshToken = tokenResponse.getRefreshToken();
        log.info("原来的refreshToken：{}", refreshToken);
        ResponseResult responseResult = tokenService.refreshToken(refreshToken);
        return responseResult;

    }
}
