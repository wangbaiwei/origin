package com.wbw.algorithm.leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 88. 合并两个有序数组
 */
public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        if (m == 0 || n == 0) {
            nums1[0] = m == 0 ? nums2[0] : nums1[0];
            return;
        }
        while (m > 0 && n > 0) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[index] = nums1[m - 1];
                m--;
            } else {
                nums1[index] = nums2[n - 1];
                n--;
            }
            index--;
        }
    }


    public static List<List<Integer>> threeSum(int[] nums) {


        List<List<Integer>> target = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++){
                for (int k = j + 1; k <  nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> numEle = new ArrayList<>();
                        numEle.add(nums[i]);
                        numEle.add(nums[j]);
                        numEle.add(nums[k]);
                        target.add(numEle); 
                    }
                }

            }
        }
        return target;

    }

    public static void main(String[] args) {

        int[] arr = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSum(arr);
        lists.stream().forEach(System.out::println);
    }
}


