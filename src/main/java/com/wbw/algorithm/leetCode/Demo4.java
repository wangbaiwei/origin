package com.wbw.algorithm.leetCode;

public class Demo4 {
    public static void main(String[] args) {
        String s1 = "yx";
        String s2 = "xy";
        int i = countChar(s1, s2);
        System.out.println(i);

        int ans = 0;
        System.out.println(ans |= 0);

    }

    public static int countChar(String str1, String str2) {
        int n = str1.length();
        int xy = 0, yx = 0;
        for (int i = 0; i < n; i++) {
            char s1 = str1.charAt(i);
            char s2 = str2.charAt(i);
            if (s1 == 'x' && s2 == 'y') {
                xy++;
            }
            if (s1 == 'y' & s2 == 'x') {
                yx++;
            }
        }
        if ((xy + yx) % 2 == 1) {
            return -1;
        }
        return xy / 2 + yx / 2 + xy % 2 + yx % 2;
    }
}
