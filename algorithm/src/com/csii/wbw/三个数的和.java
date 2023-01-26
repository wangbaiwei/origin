package com.csii.wbw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 三个数的和 {


    public List<List<Integer>> threeSum(int[] nums) {


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
        return null;
    }
}
