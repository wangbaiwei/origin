package com.wbw.algorithm.heap;

import lombok.Getter;

@Getter
public class SmallHeap extends AbstractHeap {

    private int[] arr;

    protected SmallHeap(int[] arr) {
        super(arr);
        this.arr = new int[length];
    }

    @Override
    public void genHeap(int[] arr) {
        for (int i = 0; i < length; i++) {
            int parent = (i - 1) / 2;
            while (parent >= 0 && i > 0 && arr[i] < arr[parent]) {
                int tmp = arr[i];
                arr[i] = arr[parent];
                arr[parent] = tmp;

                parent = (parent - 1) / 2;
            }
        }
        for (int i = 0; i < length; i++) {
            this.arr[i] = arr[i];
        }
    }

    public int[] getArr() {
        return arr;
    }
}
