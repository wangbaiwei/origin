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
    TOKEN_ERROR(1199, "token错误"),

    /**
     * 用户提示： 1200-1299
     */
    USER_NOT_EXIT(1200, "当前用户不存在"),

    /**
     * 计价规则不存在: 1300-1399
     */

    PRICE_RULE_EMPTY(1300, "计价规则不存在"),
    /**\
     * 地图信息：1400到1499
     */

    MAP_DISTRICT_ERROR(1400, "请求地图错误");

    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;

    }


}
