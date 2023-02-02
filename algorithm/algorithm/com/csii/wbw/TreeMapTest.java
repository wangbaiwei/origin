package com.csii.wbw;

import java.util.TreeMap;

public class TreeMapTest {

    public static void main(String[] args) {

        TreeMap<String, String> stringTreeMap = new TreeMap<>();
        stringTreeMap.put("zs", "我是zs");
        stringTreeMap.put("ls", "我是ls");
        stringTreeMap.put("ww", "我是ww");

        System.out.println(stringTreeMap.firstKey());
        System.out.println(stringTreeMap.lastKey());
        System.out.println(stringTreeMap.ceilingKey("ls")); // 大于等于ls的最近的
        System.out.println(stringTreeMap.floorKey("zs")); // 小于等于ls的最近的
    }
}
