package com.wbw.algorithm.leetCode;

import org.junit.Test;

import java.util.*;

public class CountDistinctNumbersOnBoard {
    public int distinctIntegers(int n) {

        HashSet<Integer> ans = new HashSet<>();
        HashSet<Integer> tmp = new HashSet<>();
        ans.add(n);
        long end = (long) Math.pow(10, 9);
        for (long start = end; start >= 1; start--) {
            int curLen = ans.size();
            process(ans, tmp);
            // 若长度未发生变化，跳出循环
            if (curLen == ans.size()) {
                break;
            }
        }
        return ans.size();

    }


    public void process(Set<Integer> set, Set<Integer> tmp) {

        Iterator<Integer> iterator1 = set.iterator();
        while (iterator1.hasNext()) { // 迭代的当前集合元素不可以修改
            int n = iterator1.next();
            for (int i = 1; i <= n; i++) {
                if (n % i == 1) {
                    tmp.add(i);
                }
            }
        }
        set.addAll(tmp);
        tmp.clear();
    }

    public int majorityElement(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        return list.get(0).getValue() > nums.length / 2 ? list.get(0).getKey() : -1;

    }

    @Test
    public void test() {

        List<List<Integer>> matrix = findMatrix(new int[]{1, 2, 3, 4, 4, 4, 4, 4, 5, 5});
        matrix.forEach(e -> {
            e.forEach(System.out::print);
            System.out.println();
        });
    }


    public List<List<Integer>> findMatrix(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        int subArrLen = 1;
        List<Integer> list = new ArrayList<>();
        ans.add(list);
        int curPos = 0;
        int i = 0;
        for (int num : nums) {
            for (; i < subArrLen; ) {
                List<Integer> tmp = ans.get(i);
                if (tmp.size() == 0 || tmp.get(tmp.size() - 1) != num) { // 集合为空，直接添加
                    tmp.add(num);
                    break;
                } else { // 添加新集合
                    curPos = i;
                    while ((i + 1) % subArrLen != curPos) {

                    }
                    List<Integer> newArr = new ArrayList<>();
                    newArr.add(num);
                    ans.add(newArr);
                    subArrLen++;
                    i++;
                    break;
                }
            }
        }
        return ans;
    }


}
