package com.csii.wbw;

public class Demo {

    public static void main(String[] args) {

        int[] arr = new int[] {1,1,1, 2, 2, 2, 3, 3, 3};
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }

        System.out.println(res);
    }
}
