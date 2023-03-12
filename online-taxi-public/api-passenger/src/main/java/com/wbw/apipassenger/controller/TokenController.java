package com.wbw.apipassenger.controller;

import com.alibaba.nacos.common.model.RestResult;
import com.wbw.apipassenger.response.TokenResponse;
import com.wbw.apipassenger.service.TokenService;
import com.wbw.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {

        String refreshToken = tokenResponse.getRefreshToken();
        System.out.println("原来的refreshToken：" + refreshToken);
        ResponseResult responseResult = tokenService.refreshToken(refreshToken);

        return responseResult;

    }
}
