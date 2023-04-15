package com.wbw.algorithm.leetCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/camelcase-matching/
 */
public class CamelcaseMatching {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        return Arrays.stream(queries).map(query -> judge(query, pattern)).collect(Collectors.toList());
    }

    public static boolean judge(String str, String pattern) {
        int len = str.length();
        int p = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (p < pattern.length() && c == pattern.charAt(p)) {
                p++;
            } else if (Character.isUpperCase(c)) {
                return false;
            }
        }
        return p == pattern.length();
    }

    public static void main(String[] args) {

        System.out.println(judge("FooBarT", "FoBa"));

    }
}
