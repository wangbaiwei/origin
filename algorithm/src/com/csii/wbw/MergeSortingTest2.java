package com.csii.wbw;


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MergeSortingTest2 {


    public static void main(String[] args) {

        int[] arr = new int[]{7, 3, 8, 5, 9, 2, 3, 0, 5, 3};

        int[] randomArr = getRandomArr(10);
        process(randomArr);

        System.out.println(Arrays.toString(arr));

    }

    public static int[] getRandomArr(int size) {

        IntStream intStream = Stream.generate(() -> (int) (Math.random() * 10)).limit(size).mapToInt(e -> e.intValue());
        int[] iArr = new int[size];
        AtomicInteger i = new AtomicInteger();
        intStream.forEach(e -> {
            iArr[i.getAndIncrement()] = e;
        });
        return iArr;
    }

    public static void process(int[] arr) {

        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N / 2) {
                if (mergeSize > N - L) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            mergeSize = mergeSize << 1;
        }
    }

    public static void merge(int arr[], int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= M) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 < M) help[i++] = arr[p1++];
        while (p2 < R) help[i++] = arr[p2++];

        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }
}
