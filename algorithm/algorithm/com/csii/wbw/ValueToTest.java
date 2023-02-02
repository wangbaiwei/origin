package com.csii.wbw;

import java.util.HashMap;

public class ValueToTest {
    public static void main(String[] args) {
        HashMap<Integer, String> test = new HashMap<>();
        Integer a = 19000000;
        Integer b = 19000000;
        System.out.println(a == b); // false
        test.put(b, "");

        System.out.println(test.containsKey(b)); // true：基本类型在HashMap中按照值传递

        class Zuo {
            public int value;
            public Zuo(int v) {
                value = v;
            }
        }

        Zuo z1 = new Zuo(1);
        Zuo z2 = new Zuo(1);
        System.out.println(z1 == z2); // false

        HashMap<Zuo, String> test2 = new HashMap<>();
        test2.put(z1, "我是z1");
        System.out.println(test2.containsKey(z2)); // HashMap中非基础类型，按照引用传递
    }
}
