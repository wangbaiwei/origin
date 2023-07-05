package com.wbw.algorithm.quicksort;

import com.wbw.algorithm.common.CommonUtil;

public class InsertSort {


    public static void main(String[] args) {
        int[] randomArr = CommonUtil.getRandomArr(20);

        CommonUtil.print(randomArr);
        insertSort(randomArr);
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


    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertV = arr[i];
            int index = i - 1;
            while (index >= 0 && insertV < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = insertV;
        }
    }
}
