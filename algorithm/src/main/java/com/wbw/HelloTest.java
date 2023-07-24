package com.wbw;

import org.junit.Test;

public class HelloTest {


    @Test
    public void test() {

        System.out.println(strToNum("123"));

        StringBuffer stringBuffer = new StringBuffer();
    }

    // 字符串转整数类型
    public int strToNum(String str) {
        int ans = 0;
        int len = str.length() - 1;
        for (int i = len; i >= 0; i--) {
            ans += (str.charAt(i) - '0') * Math.pow(10, len - i);
        }
        return ans;
    }


    public String addStrings(String num1, String num2) {

        int len1 = num1.length();
        int len2 = num2.length();
        int len = Math.max(len1, len2); // 取最大长度
        // 较短的数字前补充0
        if (len1 < len2) {
            num1 = String.format("%" + len + "s", num1).replaceAll(" ", "0");
        } else {
            num2 = String.format("%" + len + "s", num2).replaceAll(" ", "0");
        }
        // 保存进位
        int carry = 0;
        StringBuffer ans = new StringBuffer();
        for (int i = len - 1; i >= 0; i--) {
            int sum = getBitNum(num1, i) + getBitNum(num2, i) + carry;
            carry = sum / 10;
            ans.append(sum % 10);
        }
        return ans.reverse().toString();
    }


    // 字符串转数字
    public int getBitNum(String str, int index) {
        int bitVal = Character.digit(str.charAt(index), 10);
        return bitVal;
    }

}
