package com.wbw.algorithm.leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class FindMaxK {

    public static void main(String[] args) {


        int[] arr = new int[]{-1, 2, -3, 3};

    }


    public static int findMaxK(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>((a, b) -> b - a);
        HashSet<Integer> positive = new HashSet<>();
        HashSet<Integer> negative = new HashSet<>();

        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (num > 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }

        Iterator<Integer> iterator = negative.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (positive.contains(next)) {
                set.add(next);
            }
        }
        return set.isEmpty() ? -1 : set.stream().findFirst().get();

    }
}
