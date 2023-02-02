package com.csii.wbw;


import java.util.Arrays;
import java.util.stream.Stream;

public class MergeSortingTest {

    public static void main(String[] args) {
//        Stream.generate(() -> (int)(Math.random() * 10)).limit(10).forEach(e -> System.out.print(e + ", "));

        int[] arr = new int[]{7, 3, 8, 5, 9, 2, 3, 0, 5, 3};
        process(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);

        }

    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, M, R);

    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;

        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
    }


}
