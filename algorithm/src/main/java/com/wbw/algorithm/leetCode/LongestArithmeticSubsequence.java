package com.wbw.algorithm.leetCode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LongestArithmeticSubsequence {


    public int longestArithSeqLength(int[] nums) {

        Map<Integer, List<Integer>> ansMap = new HashMap<>();
        if (nums == null || nums.length == 1) {
            return nums.length;
        }
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {

                int pre = nums[i];
                int after = nums[j];
                int diff = after - pre;
                ansMap.put(diff, new ArrayList<>() {{
                    add(pre);
                }});
            }
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {

                int pre = nums[i];
                int after = nums[j];
                int diff = after - pre;
                ansMap.get(diff).add(after);
            }
        }


        return ansMap.values().stream().sorted(((o1, o2) -> o2.size() - o1.size())).findFirst().get().size();
    }


    public static void main(String[] args) {

    }


}
