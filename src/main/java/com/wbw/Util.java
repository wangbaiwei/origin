package com.wbw;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Util {

    public int[] getRandomArr(int size) {
        int[] arr = new int[size];
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Stream.generate(() -> (int) (Math.random() * 10)).limit(size).forEach(e -> arr[atomicInteger.getAndIncrement()] = e);
        return arr;
    }





}
