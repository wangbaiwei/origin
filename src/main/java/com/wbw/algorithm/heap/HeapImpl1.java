package com.wbw.algorithm.heap;

import com.wbw.algorithm.common.CommonUtil;
import org.junit.Test;

import java.util.Arrays;

public class HeapImpl1{
    public void heapify(int[] arr, int index, int heapSize) {
        // 左节点
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] < arr[left] ? left + 1 : left;
            largest = arr[index] < arr[largest] ? largest : index;
            if (largest == index) {
                break;
            }
            CommonUtil.swap(arr, index, largest);
            index = largest;
            left = left * 2 + 1;
        }
    }

    @Test
    public void test() {


    }

}
