package com.wbw.algorithm.presum;


import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * 前缀和-暴力递归解法
 */

@Slf4j
public class PreSumV1 extends AbstractAlgorithm {


    @Override
    public void test() {
        int[] randomArr = util.getRandomArr(5);
        System.out.println(Arrays.toString(randomArr));

        Instant start = Instant.now();
        int i = countSubArr(randomArr, 1, 3);
        Instant end = Instant.now();

        log.info("time-consuming:{}", Duration.between(start, end).toNanos()); // 4987000
        System.out.println(i);


    }


    public int countSubArr(int[] arr, int lower, int upper) {
        int count = 0;
        // 1.先用两层for循环，确定区间
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                // 每次求和前sum为0
                int sum = 0;
                // 2.第三层for循环，在指定区间移动，并求和
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                // 判断求和结果在指定区间 count++
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }

}



