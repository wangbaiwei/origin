package com.csii.wbw;

import java.util.TreeMap;
import java.util.TreeSet;

public class Demo1 {


    /**
     * 输入：key = "the quick brown fox jumps over the lazy dog", message = "vkbs bs t suepuv"
     * 输出："this is a secret"
     */


    public static void main(String[] args) {
        String key = "the quick brown fox jumps over the lazy dog";
        String message = "vkbs bs t suepuv";


        TreeMap<Character, Character> treeMap = new TreeMap<>();
        char[] chars = key.toCharArray();
        int l = 0;
        for (int i = 0; i < chars.length; i++) {
            if (treeMap.get(chars[i]) != null) {
                treeMap.put(chars[i], Character.valueOf((char)('a' + l++)));
            }
        }
        StringBuffer stringBuffer = new StringBuffer();

        char[] messages = message.toCharArray();

        for (int i = 0; i < messages.length; i++) {
            stringBuffer.append(treeMap.get(messages[i]));
        }
        System.out.println(stringBuffer);

    }



}
