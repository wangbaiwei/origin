package com.wbw.serviceprice.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

    public static double add(double v1, double v2) {
        return BigDecimal.valueOf(v1).add(BigDecimal.valueOf(v2)).doubleValue();
    }

    public static double subtract(double v1, double v2) {
        return BigDecimal.valueOf(v1).subtract(BigDecimal.valueOf(v2)).doubleValue();
    }

    public static double multiply(double v1, double v2) {
        return BigDecimal.valueOf(v1).multiply(BigDecimal.valueOf(v2)).doubleValue();
    }

    public static double divide(double v1, double v2) {
        if (v2 <= 0) {
            throw new IllegalArgumentException("除数非法");
        }
        return BigDecimal.valueOf(v1).divide(BigDecimal.valueOf(v2)).doubleValue();
    }


}
