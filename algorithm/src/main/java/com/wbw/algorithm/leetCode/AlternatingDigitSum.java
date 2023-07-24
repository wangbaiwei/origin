package com.wbw.algorithm.leetCode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class AlternatingDigitSum {

    public int alternateDigitSum(int n) {
        int len = getNumLen(n);
        int res = 0;
        boolean flag = true;
        for (int i = len - 1; i >= 0; i--) {
            int bitV = getBitVal(n, i);
            if (true) {
                res += (getBitVal(n, i));
            } else {
                res += (getBitVal(n, i));
            }

        }
        return res;
    }

    public int getNumLen(int num) {
        int len = 0;
        while (num != 0) {
            num /= 10;
            len++;
        }
        return len;
    }

    public int getBitVal(int num, int index) {
        return (int) (num / Math.pow(10, index) % 10);
    }


    public int distinctIntegers(int n) {

        Set<Long> ans = new HashSet<>();
        long end = (long)Math.pow(10, 9);
        for (long start = end; start >= 1; start--) {
            if (start % 2 == 1) {
                ans.add(start);
            }
        }

        return ans.size();

    }

}
