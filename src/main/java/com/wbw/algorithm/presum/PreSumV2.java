package com.wbw.algorithm.presum;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * 前缀和-降维
 */

@Slf4j
@Setter
@Getter
public class PreSumV2 extends AbstractAlgorithm {


    @Override
    public void test() {
        int[] randomArr = util.getRandomArr(5);
        System.out.println(Arrays.toString(randomArr));
        Instant start = Instant.now();
        PrefixSum prefixSum = new PrefixSum(randomArr, 2, 3);
        prefixSum.countSubArray();
        Instant end = Instant.now();

        log.info("time-consuming:{}", Duration.between(start, end).toNanos()); // 4987000
        System.out.println(prefixSum.count);


    }

    static class PrefixSum {
        private int count;
        private int arrSize;
        private int[] arr;
        private int lower;
        private int upper;

        public PrefixSum(int[] arr, int lower, int upper) {
            this.arr = arr;
            if (arr == null || arr.length == 0) {
                log.info("arr is empty");
                return;
            }
            this.lower = lower;
            this.upper = upper;
            this.arrSize = arr.length;
        }

        /**
         * 前缀和数组
         */
        private int[] getPrefixSum() {
            int[] arr = new int[arrSize];

            for (int i = 0; i < arrSize; i++) {
                int sum = 0;
                for (int j = 0; j <= i; j++) {
                    sum += this.arr[j];
                }
                arr[i] = sum;
            }
            return arr;
        }


        public void countSubArray() {
            int[] prefixSum = getPrefixSum();
            log.info("prefixSum: {}", Arrays.toString(prefixSum));
            int sum;
            for (int i = 0; i < prefixSum.length; i++) {
                for (int j = i; j < prefixSum.length; j++) {
                    sum = prefixSum[j] - prefixSum[i];
                    log.info("sum: {}", sum);
                    if (sum >= lower && sum <= upper) {
                        this.count++;
                    }
                }
            }
        }
    }
}



