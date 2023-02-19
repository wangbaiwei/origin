package com.wbw.algorithm.linkedList;

import com.wbw.algorithm.common.CommonUtil;

public class SingletonList {


    public static void main(String[] arr) {
        int[] randomArr = CommonUtil.getRandomArr(5);
        CommonUtil.print(randomArr);
        Node head = new Node(-1);
        head.generate(head, randomArr);
        Node reverse = reverse(head);
        while (reverse != null) {
            System.out.println(reverse.value);
            reverse = reverse.next;
        }
    }

    public static Node reverse(Node head) {
        if (head == null) {
            return head;
        }
        Node pre = null;
        Node next;
        Node cur = head.next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;

        }

        public void generate(Node head, int[] arr) {
            Node tmp = head;
            for (int i = 0; i < arr.length; i++) {
                tmp.next = new Node(arr[i]);
                tmp = tmp.next;
            }
        }
    }

}
