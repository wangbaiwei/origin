package com.wbw.algorithm.quicksort;

import com.wbw.algorithm.common.CommonUtil;
import org.junit.Test;
import java.util.Arrays;

public class RadixSort {
    public int[] sort(int[] arr) {
        // 找出数组中的最大数字
        int max = Arrays.stream(arr).max().getAsInt();
        // 最大数字的位数
        int bit = getNumLen(max);

        int len = arr.length;
        // 创建是个桶
        int[][] bucket = new int[10][len];
        // 保存每个桶中元素的个数
        int[] bucketHigh = new int[10];

        for (int i = 0; i < bit; i++) {
            // 入桶
            for (int j = 0; j < len; j++) {
                // 桶的位置
                int place = getBitVal(arr[j], i);
                // 将当前元素存入桶中，当前桶元素个数加一
                bucket[place][bucketHigh[place]++] = arr[j];
            }
            // 出桶
            int pos = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < bucketHigh[j]; k++) {
                    arr[pos++] = bucket[j][k];
                }
            }
            // 重置所有桶中元素个数为0
            for (int j = 0; j < bucketHigh.length; j++) {
                bucketHigh[j] = 0;
            }
        }
        return arr;
    }

    // 获取数字指定为的值
    public int getBitVal(int num, int index) {
        return (int)(num / Math.pow(10, index)) % 10;
    }
    // 获取数字的长度
    public int getNumLen(int num) {
        int bit = 0;
        while (num != 0) {
            num /= 10;
            bit++;
        }
        return bit;
    }

    @Test
    public void test() {

        int[] randomArr = CommonUtil.getRandomArr(15, 4);
        System.out.println(Arrays.toString(randomArr));
        System.out.println(Arrays.toString(sort(randomArr)));

    }





}
