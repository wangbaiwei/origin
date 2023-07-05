package com.wbw.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class MaxEqualRowsAfterFlips {

    public int maxEqualRowsAfterFlips(int[][] matrix) {

        Map<String, Integer> cnt = new HashMap<>();
        int n = matrix[0].length, ans = 0;
        for (int[] row : matrix) { // 遍历行
            char[] chrs = new char[n];
            for (int i = 0; i < n; i++) { // 遍历列
                chrs[i] = (char) (row[0] ^ row[i]); // 对开头为1的值，按列取反
            }
            ans = Math.max(ans, cnt.merge(String.valueOf(chrs), 1, Integer::sum));
        }
        return 0;
    }


    public static void main(String[] args) {
        Map<Integer, Integer> flags = new HashMap<>();
        Integer integer = flags.values().stream().sorted().findFirst().get();
    }
}


