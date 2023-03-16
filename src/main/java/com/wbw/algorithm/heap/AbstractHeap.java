package com.wbw.algorithm.heap;

import lombok.Getter;

import java.util.Collections;
import java.util.TreeSet;

@Getter
public abstract class AbstractHeap implements Heap{

    protected int length;

    protected AbstractHeap(int[] arr) {
        this.length = arr.length;
    }

    @Override
    public int getArrLength() {
        return length;
    }

}
