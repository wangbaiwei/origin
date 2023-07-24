package com.wbw.algorithm.leetCode;

import org.junit.Test;

import java.util.Arrays;

public class MinimumFallingPathSum {

    public int minFallingPathSum(int[][] matrix) {

        // 矩阵的长度
        int n = matrix.length;
        int[][] dp = new int[n][n];
        // 将matrix值拷贝到dp中
        System.arraycopy(matrix, 0, dp, 0, n);
        for (int i = 1; i < n; i++) { // 行
            for (int j = 0; j < n; j++) { // 列
                int min = dp[i - 1][j]; // 假设最小值为第一行的第一个值
                if (j > 0) { // 若列大于0， 取上方和左上角的最小值
                    min = Math.min(min, matrix[i - 1][j - 1]);
                }
                if (j < n - 1) { // 若列数不为最后一列，取上方和右上角的最小值
                    min = Math.min(min, matrix[i - 1][j + 1]);
                }
                dp[i][j] = min + matrix[i][j]; // 下一行每个元素值替换为左上角、上方、右上角最的最小值加当前列的值
            }
        }
        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }


    @Test
    public void test() {
        int[][] arr = new int[][]{{100, -42, -46, -41}, {31, 97, 10, -10}, {-58, -51, 82, 89}, {51, 81, 69, -51}};
        System.out.println(minFallingPathSum(arr));

        System.out.println(Math.abs(-45));
    }

  


}
