package com.wbw.algorithm.linkedList;

import com.wbw.algorithm.common.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingletonList {


    public static void main(String[] arr) {
//        int[] randomArr = CommonUtil.getRandomArr(5);
//        CommonUtil.print(randomArr);
//
//        int[] randomArr1 = CommonUtil.getRandomArr(2);
//        CommonUtil.print(randomArr1);

//        ListNode generate = CommonUtil.generate(new int[] {1});
//        CommonUtil.print(generate);
//
//        ListNode generate1 = CommonUtil.generate(new int[] {9, 9});
//        CommonUtil.print(generate1);
//
//
//        ListNode listNode = addTwoNumbers(generate, generate1);
//        CommonUtil.print(listNode);


        int[] randomArr = CommonUtil.getRandomArr(5);

        System.out.println(Arrays.toString(randomArr));
        System.out.println(Arrays.stream(randomArr).max().getAsInt());

        List<Integer> list = new ArrayList<Integer>();
        list.sort((a, b) -> b - a);
        Arrays.stream(randomArr).forEach(e -> list.add(e));


        String[] strs = new String[10];


    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode rl1 = CommonUtil.reverse(l1);
        ListNode rl2 = CommonUtil.reverse(l2);

        ListNode head = null;
        ListNode tmp = null;
        int carry = 0;

        while (rl1 != null && rl2 != null) {
            int sum = rl1.val + rl2.val + carry;
            carry = sum / 10;
            if (head == null) {
                head = new ListNode(sum % 10);
                tmp = head;
            } else {
                head.next = new ListNode(sum % 10);
                head = head.next;
            }
            rl1 = rl1.next;
            rl2 = rl2.next;
        }

        while (rl1 != null) {
            int sum = rl1.val + carry;
            carry = sum / 10;
            if (head == null) {
                head = new ListNode(sum % 10);
            } else {
                head.next = new ListNode(sum % 10);
                head = head.next;
            }
            rl1 = rl1.next;
        }

        while (rl2 != null) {
            int sum = rl2.val + carry;
            carry = sum / 10;
            if (head == null) {
                head = new ListNode(sum % 10);
            } else {
                head.next = new ListNode(sum % 10);
                head = head.next;
            }
            rl2 = rl2.next;
        }

        if (carry != 0) {
            head.next = new ListNode(carry);
        }
        return CommonUtil.reverse(tmp);

    }


}

class Solution {
    public int matrixSum(int[][] nums) {
        // 保存和
        int sum = 0;
        // 行
        int row = nums.length;
        // 列
        int col = nums[0].length;
        // 将矩阵的每行保存为List<Integer>集合
        List<Integer>[] lists = new ArrayList[row];
        for (int i = 0; i < row; i++) {
            lists[i] = new ArrayList<Integer>();
            for (int j = 0; j < col; j++) {
                lists[i].add(nums[i][j]);
            }
            // 对每行对应的集合按照从大到小的顺序排序
            lists[i].sort((a, b) -> b - a);
        }
        // 取每一列的最大值与sum相加
        for (int i = 0; i < col; i++) {
            int maxV = 0;
            for (int j = 0; j < row; j++) {
                maxV = Math.max(lists[j].get(i), maxV);
            }
            sum += maxV;
        }
        return sum;
    }

}


