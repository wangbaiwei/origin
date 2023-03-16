package com.wbw.algorithm.dp;

public class RobotWalk {


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        int process = ways2(2, 4, 4, arr.length);
        System.out.println(process);

        int i = ways3(2, 4, 4, arr.length);
        System.out.println(i);

    }


    public static int process(int cur, int target, int rest, int N) {
        if (rest == 0) { // 0列，行等于target（4）是值为1
            return cur == target ? 1 : 0;
        }
        if (cur == 1) { // 第一行：方格值为左下角的值
            return process(cur + 1, target, rest - 1, N);
        }
        if (cur == N) { // 第N行：方格值为坐上方格的值
            return process(cur - 1, target, rest - 1, N);
        }
        //  中间位置：当前方格值为左上方格值加左下方格值
        return process(cur - 1, target, rest - 1, N) + process(cur + 1, target, rest - 1, N);
    }

    public static int ways2(int cur, int target, int rest, int N) {
        int[][] dp = new int[N + 1][rest + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= rest; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(cur, target, rest, N, dp);

    }

    public static int process2(int cur, int target, int rest, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = cur == target ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(cur + 1, target, rest - 1, N, dp);
        } else if (cur == N) {
            ans = process2(cur - 1, target, rest - 1, N, dp);
        } else {
            ans = process2(cur - 1, target, rest - 1, N, dp) + process2(cur + 1, target, rest - 1, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }


    public static int ways3(int cur, int target, int rest, int N) {
        int[][] dp = new int[N + 1][rest + 1];
        // 第0行不用填值
        // 第0列target行的值为1
        dp[target][0] = 1;
        for (int col = 1; col <= rest; col++) { // 列
            // 第一行的值为左下方格的值
            dp[1][col] = dp[2][col - 1];
            for (int row = 2; row < N; row++) { // 行
                dp[row][col] = dp[row - 1][col - 1] + dp[row + 1][col - 1];
            }
            // 第N行的值为
            dp[N][col] = dp[N - 1][col - 1];
        }
        return dp[cur][rest];
    }
}
