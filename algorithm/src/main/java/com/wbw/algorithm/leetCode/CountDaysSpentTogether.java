package com.wbw.algorithm.leetCode;

/**
 * https://leetcode.cn/problems/count-days-spent-together/solution/tong-ji-gong-tong-du-guo-de-ri-zi-shu-by-1iwp/
 */
public class CountDaysSpentTogether {


    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {

        int[] dayOfMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] preSum = new int[13];
        for (int i = 0; i < preSum.length; i++) {
            preSum[i + 1] = preSum[i] + dayOfMonth[i];
        }

        int arriveAliceDay = calculate(arriveAlice, preSum);
        int arriveBobDay = calculate(arriveBob, preSum);
        int leaveAliceDay =  calculate(leaveAlice, preSum);
        int leaveBobDay = calculate(leaveBob, preSum);
        return Math.max(0, Math.min(leaveAliceDay, leaveBobDay) - Math.max(arriveAliceDay, arriveBobDay) + 1);
    }

    public int calculate(String dataStr, int[] preSum) {
        int month = Integer.parseInt(dataStr.substring(0, 2));
        int day = Integer.parseInt(dataStr.substring(3));
        return preSum[month - 1] + day;
    }

}




