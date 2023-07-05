package com.wbw.algorithm.leetCode;

import com.wbw.algorithm.common.CommonUtil;

import java.util.*;

public class Demo7 {


    public static void main(String[] args) {
//        int[] randomArr = CommonUtil.getRandomArr(5);
//        process(randomArr, 0, randomArr.length - 1);
//        CommonUtil.print(randomArr);


        Map<String, Integer> map = new HashMap<>();
        map.put("a", 12);
        map.put("b", 2);
        map.put("c", 1);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, Comparator.comparingInt(Map.Entry::getValue));
        list.forEach(e -> System.out.println(e.getKey()));
    }

    public static int binarySeach(int[] arr, int left, int right, int aim) {
        if (arr == null) {
            return -1;
        }
        if (left > right) {
            return -1;
        }

        int mid = left + ((right - left) >> 1) ;
        if (arr[mid] == aim) {
            return mid;
        } else if (arr[mid] < aim) {
            return binarySeach(arr, left + 1, right, aim);
        } else if (arr[mid] > aim) {
            return binarySeach(arr, left, right - 1, aim);
        }

        return -1;
    }



    // O(N * logN)
    public static void process(int[] arr, int L, int R) {
        if (L == R) { // base case
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

}
