package com.wbw.algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RearrangeBarcodes {


    public static int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : barcodes) map.put(num, map.getOrDefault(num, 0) + 1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(new int[]{entry.getValue(), entry.getKey()});
        }

        int len = barcodes.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int[] p = pq.poll();
            int num = p[1];
            int count = p[0];
            if (i == 0 || res[i - 1] != num) {
                res[i] = num;
                if (count > 1) {
                    pq.offer(new int[]{count - 1, num});
                }
            } else {
                int[] p2 = pq.poll();
                int num2 = p2[1];
                int count2 = p2[0];
                if (count2 > 1) {
                    pq.offer(new int[]{count2 - 1, num2});
                }
                pq.offer(new int[]{count, num});
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 1, 2, 3};
        rearrangeBarcodes(arr);


    }
}
