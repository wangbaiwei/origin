package com.wbw.algorithm.quicksort;

import com.wbw.algorithm.common.CommonUtil;

public class SelectSort {
    public static void main(String[] args) {
        int[] randomArr = CommonUtil.getRandomArr(6);
        int[] sort = sort(randomArr);
        CommonUtil.print(sort);


    }


    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }
}
