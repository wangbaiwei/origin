package com.csii.wbw;

public class 数字反转 {

    public static void main(String[] args) {
        System.out.println(reverse(1122267899));
    }


    public static int reverse(int num) {
        int res = 0;
        while (num != 0) {
            if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
                return -1;
            }
            int tmp = num % 10;
            res = res * 10 + tmp;
            num /= 10;
        }
        return res;
    }


}
