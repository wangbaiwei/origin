package com.wbw.algorithm.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxCover {


    public static int maxCover1(int[][] lines) {
        // 所有线段开始值的最小值
        int min = Arrays.stream(lines).mapToInt(e -> e[0]).min().getAsInt();
        // 所有线段结束值的最大值
        int max = Arrays.stream(lines).mapToInt(e -> e[1]).max().getAsInt();

        int ans = 0;
        for (double i = min + 0.5; i < max; i++) {
            int curCover = 0; // 每个刻度上线段的覆盖数
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] < i && lines[j][1] > i) {
                    curCover++;
                }
            }
            // 保留重叠线段的最大值
            ans = Math.max(ans, curCover);
        }
        return ans;
    }


    public static int maxCover2(int[][] lines) {

        // 所有线段按开始值从小到大排序
        Arrays.sort(lines, (a, b) -> a[0] - b[0]);

        int ans = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < lines.length; i++) {
            // 堆不为空，且当前线段的开始值大于等于前一次线段的结束值（当前线段和前一次线段不存在重合）
            // 将前一个线段的结束值移除堆
            while (!heap.isEmpty() && lines[i][0] >= heap.peek()) {
                heap.poll();
            }
            // 将当前线段的结束值保存在堆中
            heap.offer(lines[i][1]);
            // 求堆中元素的个数，即为当前 线段的最大重叠数
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2}, {2, 4}, {4, 5}};
        int i = maxCover2(arr);
        System.out.println(i);
    }
}
