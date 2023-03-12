package com.wbw.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {

    /**
     * 错误验证码：1000-1099
     */
    VERIFICATION_CODE_ERROR(1099, "验证码不正确"),
    SUCCESS(1, "success"),
    FAIL(0, "fail"),

    /**
     * Token类提示 1100-1199
     */
    TOKEN_ERROR(1199, "token错误");


    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;

    }


}
