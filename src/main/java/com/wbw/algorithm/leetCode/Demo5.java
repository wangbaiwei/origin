package com.wbw.algorithm.leetCode;

public class Demo5 {

    public static void main(String[] args) {
        System.out.println(Long.toBinaryString(64));
        System.out.println(Long.toBinaryString(-64));

        System.out.println(Long.toBinaryString(0xffff));

        System.out.println(Long.toBinaryString(~(1 << 31)));
        System.out.println(Long.toBinaryString((0x0001L << 48)));
    }
}
