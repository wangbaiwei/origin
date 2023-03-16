package com.wbw.algorithm.presum;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 前缀和-降维
 */

@Slf4j
@Setter
@Getter
public class PreSumV4 extends AbstractAlgorithm {


    @Override
    public void test() {

    }

    public static void main(String[] args) {
        int[] randomArr = {-2147483647, 0, -2147483647, 2147483647};
        long[] preSumArr = getPreSumArr(randomArr);
        int lower = -564;
        int upper = 3864;
        System.out.println(count(preSumArr, 0, preSumArr.length - 1, lower, upper));


    }

    public static long[] getPreSumArr(int[] arr) {
        // 数组前缀和用long
        long[] tmpArr = new long[arr.length];
        tmpArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            tmpArr[i] = tmpArr[i - 1] + arr[i];
        }
        return tmpArr;
    }

    public static int count(long[] arr, int l, int r, int lower, int upper) {
        if (l == r) {
            if (arr[l] >= lower && arr[l] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        int m = l + ((r - l) >> 1);
        return count(arr, l, m, lower, upper) + count(arr, m + 1, r, lower, upper) + merge(arr, l, m, r, lower, upper);
    }

    private static int merge(long[] nums, int l, int m, int r, int lower, int upper) {

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



