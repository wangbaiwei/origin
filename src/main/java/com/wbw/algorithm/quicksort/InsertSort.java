package com.wbw.algorithm.quicksort;

import com.wbw.algorithm.common.CommonUtil;

public class InsertSort {


    public static void main(String[] args) {
        int[] randomArr = CommonUtil.getRandomArr(20);

        CommonUtil.print(randomArr);
        sort(randomArr);
        CommonUtil.print(randomArr);
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int j = i + 1;
            while (j > 0 && arr[j] < arr[j - 1]) {
                int tmp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = tmp;
                j--;
            }
        }
    }
}
