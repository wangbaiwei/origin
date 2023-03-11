package com.wbw.internalcommon.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wbw.internalcommon.dto.TokenResult;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    // 盐
    private static final String SIGN = "GPFwbw!@Hello";

    // 手机
    private static final String JWT_KEY_PHONE = "phone";
    // 身份标识 0-乘客 1-司机
    private static final String JWT_KEY_IDENTITY = "identity";


    /**
     * 生成token
     *
     * @param passengerPhone 手机号
     * @return
     */
    public static String generatorToken(String passengerPhone, String identity) {

        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);

        // token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date time = calendar.getTime();
        JWTCreator.Builder builder = JWT.create();

        // 整合map
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        // 整合过期时间
//        builder.withExpiresAt(time);

        // 生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;

    }

    /**
     * 解析token
     */
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }

    public static void main(String[] args) {
        String token = generatorToken("18669945566", "1");
        System.out.println("生成token的值：" + token);
        TokenResult tokenResult = parseToken(token);
        System.out.println("解析后的手机号：" + tokenResult.getPhone() + " 身份：" + tokenResult.getIdentity());
    }
}
