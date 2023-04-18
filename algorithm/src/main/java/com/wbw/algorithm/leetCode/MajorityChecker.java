package com.wbw.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

public class MajorityChecker {

    private int[] arr;

    public MajorityChecker(int[] arr) {
        this.arr = arr;
    }

    public int query(int left, int right, int threshold) {

        Map<Integer, Integer> numTimes = new HashMap<>();
        for (int le = left; le <= right; le++) {
            numTimes.put(arr[le], numTimes.getOrDefault(arr[le], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : numTimes.entrySet()) {
            if (entry.getValue() > threshold) {
                return entry.getKey();
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = new int[] {1,1,2,2,1,1};
        MajorityChecker majorityChecker = new MajorityChecker(arr);
        System.out.println(majorityChecker.query(2, 3, 2));
    }
}
