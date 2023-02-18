package com.wbw.algorithm.common;

public class CommonUtil {


    public static int[] getRandomArr(int size) {
        int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            ints[i] = ((int) (Math.random() * 10));
        }
        return ints;
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
