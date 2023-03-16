package com.wbw.algorithm.slideWindow;

import java.util.LinkedList;

public class SlideWindow2 {

    public int num(int[] arr, int taget) {
        int ans = 0;
        int N = arr.length;
        LinkedList<Integer> minList = new LinkedList<>();
        LinkedList<Integer> maxList = new LinkedList<>();

        int R = 0;
        for (int L = 0; L < N; L++) {
            while (R < N) {

                while (!maxList.isEmpty() && arr[R] > arr[maxList.peekLast()]) {
                    maxList.pollLast();
                }
                maxList.addLast(arr[R]);
                while (R < N && !minList.isEmpty() && arr[R] < arr[minList.peekLast()]) {
                    minList.pollLast();
                }
                minList.addLast(arr[R]);
                // 窗口过期
                if (minList.peekFirst() == L) {
                    minList.pollFirst();
                }
                if (maxList.peekFirst() == L) {
                    maxList.pollFirst();
                }
                // 计算满足条件的子数组
                if (maxList.peekFirst() - minList.peekFirst() <= taget) {
                    ans += R - L;
                }
                R++;
            }

        }
        return ans;
    }
}


