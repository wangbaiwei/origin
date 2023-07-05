package com.wbw.algorithm.leetCode;

import java.util.concurrent.Executors;

public class CountTime {

    public static int countTime(String time) {
        char[] chrs = time.toCharArray();
        int res = 0;
        for (int i = 0; i < chrs.length; i++){
            if (chrs[i] == '?'){
                for (char j = '0'; j <= '9'; j++) {
                    chrs[i] = j;
                    if (check(chrs)){
                        res++;
                    }
                }
            }
        }
        return res;


    }

    public static boolean check(char[] times) {
        int hour = (times[0] - '0') * 10 + times[1] - '0';
        int minutes = (times[3] - '0') * 10 + (times[4] - '0');
        return hour < 24 && minutes < 60;
    }

    public static void main(String[] args) {

//        System.out.println(countTime("?5:00"));

        Executors.newSingleThreadExecutor();


    }
}
