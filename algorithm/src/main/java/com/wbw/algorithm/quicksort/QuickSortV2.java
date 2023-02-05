package com.wbw.algorithm.quicksort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class QuickSortV2 extends AbstractSort {


    @Override
    public int[] sort(int[] arr) {
        return new int[0];
    }




    @Override
    public void test() {

        int[] randomArr = util.getRandomArr(5);
        log.info("before sort arr:{}", randomArr);

        process(randomArr, 0, randomArr.length - 1);
        log.info("arr:{}", randomArr);

    }

    public void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int M = partition(arr, L, R);
        process(arr, L, M - 1);
        process(arr, M + 1, R);
    }

    private int partition(int[] arr, int L, int R) {
        if (L > R) { // L > R
            return -1;
        }
        if (L == R) {
            return L;
        }

        int lessPoint = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessPoint);
            }
            index++;
        }
        swap(arr, ++lessPoint, R); // ++lessPoint
        return lessPoint;
    }


    public void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }


}
