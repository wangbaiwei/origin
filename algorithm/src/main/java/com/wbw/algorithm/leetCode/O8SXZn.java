package com.wbw.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class O8SXZn {


    public static int storeWater(int[] bucket, int[] vat) {
        int minIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] < bucket[minIndex]) {
                minIndex = i;
            }
        }
        bucket[minIndex] += 1;

        int[][] curVol = new int[bucket.length][2];
        for (int i = 0; i < curVol.length; i++) {
            curVol[i][0] = 0;
            curVol[i][1] = vat[i];
        }

        boolean flag = false;
        Map<Integer, Boolean> flags = new HashMap<>();
        Map<Integer, Integer> ans = new HashMap<>();
        while (!flag){


            for (int i = 0; i < bucket.length; i++) {
                if (curVol[i][0] >= curVol[i][1]) {
                    flags.put(i, true);
                    continue;
                }

                curVol[i][0] += bucket[i];
                ans.put(i, ans.getOrDefault(i, 0) + 1);

            }

            if (flags.size() == bucket.length && !flags.containsValue(false)) {
                flag = true;
            }
        }


        return ans.values().stream().sorted((a, b) -> b - a).findFirst().get();

    }


    public static void main(String[] args) {
        storeWater(new int[]{3, 2, 5}, new int[]{0, 0, 0});

    }


}
