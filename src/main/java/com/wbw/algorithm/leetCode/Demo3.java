package com.wbw.algorithm.leetCode;

import java.util.*;

public class Demo3 {
    public String bestHand(int[] ranks, char[] suits) {
        char c = suits[0];
        for (int i = 0; i < suits.length; i++) {
            if (suits[i] != c) break;
            else return "Flush";
        }
        Map<Character, Integer> strMap = new HashMap<>();
        for (int i = 0; i < suits.length; i++) {
            strMap.put(suits[i], strMap.getOrDefault(suits[i], 0));
        }

        ArrayList<Map.Entry<Character, Integer>> entries = new ArrayList<>(strMap.entrySet());
        Collections.sort(entries, (o1, o2) -> o1.getValue() - o2.getValue());
        int value = entries.get(0).getValue();
        if (3 == value) {
            return "Three of a kind";
        } else if (2 == value) {
            return "Pair";
        } else if (1 == value) {
            return "High Card";
        }
        return null;
    }
}
