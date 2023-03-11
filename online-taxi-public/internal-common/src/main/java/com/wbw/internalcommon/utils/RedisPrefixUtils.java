package com.wbw.internalcommon.utils;

public class RedisPrefixUtils {

    // 乘客验证码前缀
    private static String verificationCodePrefix = "passenger-verification-code-";

    //
    private static String tokenPrefix = "token_";


    /**
     * 根据手机号生成key
     *
     * @param passengerPhone
     * @return
     */
    public static String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 根据手机号和身份标识生成token
     *
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone, String identity) {

        return tokenPrefix + phone + "_" + identity;

    }
}
