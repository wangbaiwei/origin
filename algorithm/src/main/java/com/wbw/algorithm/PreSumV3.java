package com.wbw.algorithm;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 前缀和-降维
 */

@Slf4j
@Setter
@Getter
public class PreSumV3 extends AbstractAlgorithm {


    @Override
    public void test() {

//        int[] randomArr = util.getRandomArr(4);
        int[] randomArr = {-2147483647,0,-2147483647,2147483647};
        PrefixSum prefixSum = new PrefixSum(randomArr, -564, 3864);
        log.info("count:{}", prefixSum.count);

    }

    static class PrefixSum {
        private int count;
        private int arrSize;
        private int[] sourceArr;
        private long[] preSumArr;

        public PrefixSum(int[] arr, int lower, int upper) {
            log.info("this.arr: {}", Arrays.toString(arr));
            this.sourceArr = arr;
            this.arrSize = arr.length;
            getPreSum();
            this.count = this.count(preSumArr, 0, preSumArr.length - 1, lower, upper);
        }

        public void getPreSum() {
            long[] arr = new long[arrSize];
            arr[0] = this.sourceArr[0];
            for (int i = 1; i < arrSize; i++) {
                // arr[i - 1] + this.arr[i]
                arr[i] = arr[i - 1] + this.sourceArr[i];
            }
            log.info("preSumArr:{}", arr);
            this.preSumArr = arr;
        }

        private int count(long[] nums, int L, int R, int lower, int upper) {
            if (L == R) {
                if (nums[L] >= lower && nums[L] <= upper) {
                    return 1;
                } else {
                    return 0;
                }
            }
            int M = L + ((R - L) >> 1);
            return count(nums, L, M, lower, upper) + count(nums, M + 1, R, lower, upper) + merge(nums, L, M, R, lower, upper);
        }

        private int merge(long[] nums, int l, int m, int r, int lower, int upper) {

            int ll = l;
            int lr = l;
            int ans = 0;

            for (int i = m + 1; i <= r; i++) {
                long min = nums[i] - upper;
                long max = nums[i] - lower;
                while (lr <= m && nums[lr] <= max) {
                    lr++;
                }
                while (ll <= m && nums[ll] < min) {
                    ll++;
                }
                ans += lr - ll;
            }

            int p1 = l;
            int p2 = m + 1;
            long[] help = new long[r - l + 1];
            int i = 0;
            while (p1 <= m && p2 <= r) {
                help[i++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
            }
            while (p1 <= m) help[i++] = nums[p1++];
            while (p2 <= r) help[i++] = nums[p2++];
            for (int j = 0; j < help.length; j++) {
                nums[l + j] = help[j];
            }
            return ans;
        }
    }
}



