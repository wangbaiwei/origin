package com.csii.wbw;

import java.util.*;

public class 火车进站 {

    static List<String> strs = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        while (in.hasNext()) { // 注意 while 处理多个 case
            strs.clear();
            int n = in.nextInt();
            int[] id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = in.nextInt();
            }
            Stack<Integer> stack = new Stack<>();
            tainOut(id, 0, stack, 0, "");
            Collections.sort(strs);
            for (String str : strs) {
                System.out.println(str);
            }

        }
    }

    public static void tainOut(int[] id, int i, Stack<Integer> s, int o,
                               String str) {
        if (o == id.length) {
            strs.add(str);
        }

        if (!s.isEmpty()) {
            int tmp = s.pop();
            tainOut(id, i, s, o + 1, str + " ");
            s.push(tmp);
        }

        if (i < id.length) {
            s.push(id[i]);
            tainOut(id, i + 1, s, o, str);
            s.pop();
        }
    }
}
