package com.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Slf4j
public class MergeSortingTest2 {

    @Test
    public void test() {
        int[] randomArr = getRandomArr(4);

        randomArr = new int[]{3, 4, 8, 7};
        System.out.println(smallSum(randomArr, 0, randomArr.length - 1));
    }

    public int smallSum(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return smallSum(arr, L, M) +
                smallSum(arr, M + 1, R) + merge(arr, L, M, R);
    }

    public int merge(int[] arr, int L, int M, int R) {
        int ans = 0;
        int[] ints = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            ans += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            ints[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) ints[i++] = arr[p1++];
        while (p2 <= R) ints[i++] = arr[p2++];
        for (int j = 0; j < ints.length; j++) {
            arr[L + j] = ints[j];
        }
        return ans;

    }

    /**
     * @param size
     * @return 随机数组
     */
    public static int[] getRandomArr(int size) {
        int[] ints = new int[size];
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Stream.generate(() -> (int) (Math.random() * 10)).limit(size).forEach(e -> ints[atomicInteger.getAndIncrement()] = e);
        return ints;
    }

}