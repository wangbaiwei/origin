package com.wbw.algorithm.slideWindow;

import java.util.LinkedList;
import java.util.List;

public class SlideWindow1 {


    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        int[] ans = new int[N - k + 1];
        int index = 0;
        LinkedList<Integer> lists = new LinkedList<>();
        int R = 0;
        while (R < N) {
            // 移除双端队列中索引位置值大于num中当前位置值
            while (!lists.isEmpty() && nums[R] < nums[lists.peekFirst()]) {
                lists.pollLast();
            }
            lists.addLast(R);

            // 若队列头部的索引值等于尾部索引值 - k：窗口过期，移除头部索引
            if (lists.peekFirst() == R - k) {
                lists.pollFirst();
            }
            // 形成窗口，记录当前窗口最大值
            if (R >= k - 1) {
                ans[index++] = nums[lists.peekFirst()];
            }
            R++;
        }
        return ans;
    }
}
