package com.wbw.algorithm.quicksort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuickSortV1 extends AbstractSort {


    @Override
    public int[] sort(int[] arr, int target) {

        // 设置初始小于目标区域边界为-1
        int smallArea = -1;
        int tmp;
        for (int i = 0; i < arr.length; i++) {
            // 当前数小于目标数，当前数和区域下一个数交换位置，区域边界后移
            if (arr[i] <= target) {
                if (smallArea + 1 < arr.length) {
                    tmp = arr[i];
                    arr[i] = arr[smallArea + 1];
                    arr[smallArea + 1] = tmp;
                    smallArea++;
                }
            } else if (arr[i] > target) { // 当前数大于目标数跳过
                continue;
            }
        }
        log.info("smallArea:{}", smallArea);
        return arr;
    }

    @Override
    public void test() {

        int[] randomArr = util.getRandomArr(5);
        log.info("before sort arr:{}", randomArr);
        int[] sort = sort(randomArr, 5);
        log.info("arr:{}", sort);

    }
}
