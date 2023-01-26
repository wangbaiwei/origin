package com.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * 小和：就是遍历一个数组，将每个数组前面比自己小的数字累加求和
 */

@Slf4j
public class SmallSum {

    /**
     * 你是这样写的吗？
     */
    @Test
    public void test() {
        int[] arr = new int[]{1, 1, 2, 6, 4};
        log.info("sort before：{}", Arrays.toString(arr));

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                sum += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        log.info("小和为：{}", sum);
    }

    /**
     * 使用归并排序求小和
     */
    @Test
    public void test2() {
        int[] arr = new int[]{1, 1, 2, 6, 4};
        int process = process(arr, 0, arr.length - 1);
        System.out.println(process);

    }
    public  int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return  process(arr, L, M) +
        process(arr, M + 1, R) + merget(arr, L, M, R);
    }

    public int merget(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int ans = 0;
        while (p1 <= M && p2 <= R) {
            ans += arr[p1] < arr[p2] ? arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) help[i++] = arr[p1++];
        while(p2 <= R) help[i++] = arr[p2++];
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
        return ans;

    }


    public int[] getArr(int size) {
        int[] ints = new int[size];
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Stream.generate(() -> (int) (Math.random() * 10)).limit(size).forEach(e -> ints[atomicInteger.getAndIncrement()] = e);
        return ints;
    }

}
