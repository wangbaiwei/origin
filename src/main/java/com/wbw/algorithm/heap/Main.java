package com.wbw.algorithm.heap;

import com.wbw.algorithm.common.CommonUtil;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

        int[] randomArr = CommonUtil.getRandomArr(5);

        HeapManager heapManager = new HeapManager();

        AbstractHeap smallHeap = new SmallHeap(randomArr);
        AbstractHeap bigHeap = new BigHeap(randomArr);
        heapManager.add(smallHeap);
        heapManager.add(bigHeap);
        heapManager.process(randomArr);


        System.out.println(Arrays.toString(smallHeap.getArr()));
        System.out.println(Arrays.toString(bigHeap.getArr()));



    }


}
