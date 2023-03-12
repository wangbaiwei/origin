package com.wbw.apipassenger.interceptor;

import com.wbw.internalcommon.constant.TokenConstans;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.internalcommon.dto.TokenResult;
import com.wbw.internalcommon.utils.JwtUtils;
import com.wbw.internalcommon.utils.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;

        String resultString = "";
        String token = request.getHeader("Authorization");
        System.out.println("token: " + token);

        TokenResult tokenResult = JwtUtils.checkToken(token);

        // 从redis中取出token
        if (tokenResult == null) {
            resultString = "token invalid";
            result = false;
        } else {
            // 拼接key
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();

            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstans.ACCESS_TOKEN_TYPE);
            // 从redis中取出token
            String tokenReids = stringRedisTemplate.opsForValue().get(tokenKey);
            if (StringUtils.isBlank(tokenReids) || !token.trim().equals(tokenReids.trim())) {
                resultString = "token invalid";
                result = false;
            }
        }


        if (!result) {
            PrintWriter out = response.getWriter();
            out.println(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }
        return result;
    }

}
