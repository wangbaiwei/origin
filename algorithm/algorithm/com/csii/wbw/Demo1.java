package com.csii.wbw;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Demo1 {


    /**
     * 输入：key = "the quick brown fox jumps over the lazy dog", message = "vkbs bs t suepuv"
     * 输出："this is a secret"
     */


    public static void main(String[] args) {
        String key = "the quick brown fox jumps over the lazy dog";
        String message = "vkbs bs t suepuv";


        Map<Character, Character> hashMap = new LinkedHashMap<>();
        char[] keys = key.toCharArray();
        int l = 0;
        for (int i = 0; i < keys.length; i++) {
            if (!hashMap.containsKey(keys[i]) && keys[i] != ' ') {
                hashMap.put(keys[i], Character.valueOf((char) ('a' + l++)));
            }
        }
        hashMap.forEach((k, v) -> System.out.print(k + " "));
        System.out.println();
        hashMap.forEach((k, v) -> System.out.print(v + " "));

        StringBuffer stringBuffer = new StringBuffer();
        char[] messages = message.toCharArray();
        for (int i = 0; i < messages.length; i++) {
            stringBuffer.append(hashMap.getOrDefault(messages[i], ' '));
        }

    }



}
