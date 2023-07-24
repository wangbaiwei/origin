package com.wbw.algorithm.leetCode;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Stack;


public class WHec2 {

    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            pq.offer(cur);
            cur = next;
        }

        ListNode node = null;
        ListNode newHead = null;
        while (pq.size() > 0) {
            if (node == null) {
                node = pq.poll();
                newHead = node;
            } else {
                node.next = pq.poll();
                node = node.next;
            }

        }

        return newHead;

    }


    @Test
    public void test() {
        // [4,2,1,3]
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode listNode = sortList(head);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    @Test
    public void test2() {
        Stack<Integer> stack = new Stack<>();
        stack.push(null);
        System.out.println(stack.isEmpty()); // false
    }

}

