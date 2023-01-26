package com.test;

import org.junit.Test;

public class BiggerThanRightTwice {

    @Test
    public void test() {

        int[] ints = {4, 1, 2, 6, 3, 2};
        System.out.println(process(ints, 0, ints.length - 1));


    }

    public int process(int[] arr, int L, int R) {
        if (L == R) return 0;
        int M = L + ((R - L) >> 1);
        return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    private int merge(int[] arr, int l, int m, int r) {

        int ans = 0;
        int windowR = m + 1;
        for (int i = l; i <= m; i++) {
            while (windowR <= r && arr[i] > (arr[windowR] * 2)) {
                windowR++;
            }
            ans += windowR - m - 1;
        }

        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return ans;
    }
}
