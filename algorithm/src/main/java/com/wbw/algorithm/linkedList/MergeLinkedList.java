package com.wbw.algorithm.linkedList;

import com.wbw.algorithm.common.CommonUtil;

import java.util.*;

public class MergeLinkedList {


    public static void main(String[] args) {
        int[] randomArr1 = CommonUtil.getRandomArr(3);
        Arrays.sort(randomArr1);
        int[] randomArr2 = CommonUtil.getRandomArr(3);
        Arrays.sort(randomArr2);
        int[] randomArr3 = CommonUtil.getRandomArr(3);
        Arrays.sort(randomArr3);
        Node head1 = new Node(-1);
        Node head2 = new Node(-1);
        Node head3 = new Node(-1);
        generate(head1, randomArr1);
        generate(head2, randomArr2);
        generate(head3, randomArr3);
        List<Node> nodes = new ArrayList<>() {{
            add(head1);
            add(head2);
            add(head3);
        }};

        Node merge = merge(nodes);
        while (merge != null) {
            System.out.println(merge.date);
            merge = merge.next;
        }

    }

    public static Node merge(List<Node> nodes) {

        PriorityQueue<Node> objects = new PriorityQueue<>((Node o1, Node o2) -> o1.date - o2.date);
        for (Node node : nodes) {
            objects.add(node.next);
        }

        Node head = objects.poll();
        Node pre = head;
        if (pre.next != null) {
            objects.add(pre.next);
        }
        while (!objects.isEmpty()) {
            Node cur = objects.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                objects.add(cur.next);
            }
        }
        return head;
    }


    public static void generate(Node head, int[] arr) {
        Node tmp = head;
        for (int i = 0; i < arr.length; i++) {
            tmp.next = new Node(arr[i]);
            tmp = tmp.next;
        }
    }


    static class Node {
        int date;
        Node next;

        Node(int date) {
            this.date = date;
        }
    }
}
