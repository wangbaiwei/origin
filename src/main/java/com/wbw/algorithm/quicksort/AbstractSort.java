package com.wbw.algorithm.quicksort;

import com.wbw.Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

public abstract class AbstractSort implements Sort{

    public AbstractSort() {}

    @Override
    public int[] sort(int[] arr) {
        return new int[0];
    }

    @Override
    public int[] sort(int[] arr, int target) {
        return new int[0];
    }

    @Test
    public abstract void test();

    protected Util util = new Util();
}
