package com.wbw.algorithm.leetCode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
 */
public class BinaryRepresentationToOne {
    public int numSteps(String s) {

        int n = s.length();
        boolean meet = false;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {

            int index = s.charAt(i);
            if (index == '0') {
                ans += (!meet ? 1 : 2);
            } else {
                if (!meet) {
                    ans += 2;
                    meet = true;
                } else {
                    ans += 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int hello = new BinaryRepresentationToOne().numSteps("1101");
        System.out.println(hello);

    }

}
