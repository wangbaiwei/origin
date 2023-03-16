package com.wbw.algorithm.heap;

import java.util.ArrayList;
import java.util.List;

public class HeapManager {

    private List<Heap> heaps = new ArrayList<>();

    public HeapManager() {
    }

    public void add(Heap heap) {
        this.heaps.add(heap);
    }

    public void process(int[] arr) {
        for (Heap heap : heaps) {
            heap.genHeap(arr);
        }
    }


}
