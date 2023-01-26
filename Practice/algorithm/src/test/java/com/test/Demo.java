package com.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

@Slf4j
public class Demo {



    @Test
    public void test() {

        int[] ints = {2, 1, 4, 3};

        process(ints, 0, ints.length - 1);

    }


    public void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M + 1,R);
        merge(arr, L, M, R);
    }

    private void merge(int[] arr, int l, int m, int r) {
        System.out.println(Arrays.toString(arr) + " l:" + l + " m:"+m + " r:" + r);
    }

}