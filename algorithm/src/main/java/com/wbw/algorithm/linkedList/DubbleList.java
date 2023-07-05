package com.wbw.algorithm.linkedList;

import com.wbw.algorithm.common.CommonUtil;

import java.util.Arrays;

public class DubbleList {


    public static void main(String[] args) {
        int[] randomArr = CommonUtil.getRandomArr(5);
        System.out.println(Arrays.toString(randomArr));
        Node dubbleList = getDubbleList(randomArr);


        // 反转链表
        Node reverse = reverse(dubbleList);
        while (reverse != null) {
            System.out.printf("%d\t", reverse.date);
            reverse = reverse.next;
        }

        System.out.println(test());

    }

//    public static Node reverse(Node head) {
//         Node next; // 保存下一个节点
//         Node last = null; // 保存上一个节点
//         Node cur = head.next; // 当前节点从head.next开始
//         while (cur != null) {
//             next = cur.next;
//             cur.next = last;
//             cur.last = next;
//             last = cur;
//             cur = next;
//         }
//         return last;
//    }

    public static Node getDubbleList(int[] arr) {
        Node head = new Node(-1);
        Node tmp = head;
        Node last = null;
        for (int i : arr) {
            tmp.next = new Node(i);
            tmp.last = last;
            tmp = tmp.next;
        }
        return head;
    }


    static class Node {
        public Node last;
        public Node next;
        public int date;

        public Node(int date) {
            this.date = date;
        }
    }


    public static Node reverse(Node head) {
        if (head == null){
            return null;
        }

        Node last = null;
        Node next;
        Node cur = head;
        while (cur != null) {
            // 保存下一个节点
            next = cur.next;
            // 下一个节点指向前一个节点
            cur.next = last;
            cur.last = next;
            last = cur;
            cur = next;
        }

        return last;
    }



    public static int test() {
        try {
            return 10 / 2;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } finally {
            return 2;
        }
    }
}
