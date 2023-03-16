package com.wbw.algorithm.quicksort;

import com.wbw.algorithm.common.CommonUtil;
import org.springframework.util.StopWatch;

public class BubbleSort {

    public static void main(String[] args) {
        int[] randomArr = CommonUtil.getRandomArr(100000);
        sort(randomArr);
//        CommonUtil.print(randomArr);
    }


    public static void sort(int[] arr) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }
}
