package com.csii.wbw;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class 罗马数字转整数 {

    public Map<Character, Integer> symbolValue = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};


    public int romanToInt(String str) {
        int len = str.length();
        int res = 0;
        for (int i = 0; i < len; i++) {
            Integer curValue = symbolValue.get(str.charAt(i));
            if (i + 1 < len &&  curValue < symbolValue.get(str.charAt(i + 1))) {
                res -= symbolValue.get(str.charAt(i));
            } else {
                res += curValue;
            }
        }
        return res;
    }

    @Test
    public void test() {
        String romanStr = "VI";
        int intValue = romanToInt(romanStr);
        System.out.println(intValue);

    }
}
