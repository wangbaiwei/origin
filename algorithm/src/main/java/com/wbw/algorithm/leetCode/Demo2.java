package com.wbw.algorithm.leetCode;

import java.util.TreeSet;
import java.util.function.BinaryOperator;

public class Demo2 {

    public static void main(String[] args) {
        int abc = partitionString("abc");
        System.out.println(abc);
    }




    public static int partitionString(String s) {

        TreeSet<String> ans = new TreeSet<>();
        TreeSet<Character> ts = new TreeSet<>();
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j <= i; j++) {
                ts.add(chars[j]);
            }
            ts.forEach(character -> sb.append(character));
            ans.add(sb.toString());
            sb.setLength(0);
            ts.clear();
        }
        return ans.size();
    }
}
