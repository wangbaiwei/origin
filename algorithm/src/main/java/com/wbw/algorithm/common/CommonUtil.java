package com.wbw.algorithm.common;

import com.wbw.algorithm.linkedList.ListNode;

import java.util.Arrays;
import java.util.stream.Stream;

public class CommonUtil {


    public static int[] getRandomArr(int size) {
        return Stream.generate(() -> (int) (Math.random() * 10)).limit(size).mapToInt(e -> e).toArray();
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(ListNode head) {
        ListNode tmp = head;
        StringBuffer stringBuffer = new StringBuffer();
        while (tmp != null) {
            stringBuffer.append(tmp.val).append(",");
            tmp = tmp.next;
        }
        System.out.println(stringBuffer.substring(0, stringBuffer.length() - 1));
    }


    public static ListNode generate(int[] arr) {
        ListNode head = null;
        ListNode tmp = head;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                head = new ListNode(arr[i]);
                tmp = head;
            } else {
                tmp.next = new ListNode(arr[i]);
                tmp = tmp.next;
            }
        }
        return head;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next;
        ListNode cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
