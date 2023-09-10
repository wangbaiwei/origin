package com.wbw;

import com.wbw.algorithm.common.CommonUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

public class Review {


    @Test
    public void test() {

        int[] randomArr = {-1, 2, -8, -10};
        System.out.println(Arrays.toString(randomArr));
        quickSort(randomArr);
        System.out.println(Arrays.toString(randomArr));

    }


    public void quickSort(int[] arr) {
        quick(arr, 0, arr.length - 1);

    }

    private void quick(int[] arr, int l, int r) {
        if (l > r) {
            return;
        }
        int partitoin = partition(arr, l, r);
        quick(arr, 0, partitoin - 1);
        quick(arr, partitoin + 1, r);

    }

    private int partition(int[] arr, int start, int end) {
        if (start == end) {
            return start;
        }
        int index = start;
        int target = arr[index];
        while (start < end && arr[end] >= target) {
            end--;
        }
        while (start < end && arr[start] <= target) {
            start++;
        }
        swap(arr, start, end);
        swap(arr, index, start);
        return start;
    }

    public void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    // 生成回文数字
    public int[] getPalindromArray(int size, int radical) {
        int[] res = new int[size];
        if (size % 2 == 0) { // 若长度为偶数，第二次翻转拼接即可
            int[] randomArr = getRandomArr(size / 2, radical);
            System.arraycopy(randomArr, 0, res, 0, size / 2);
            reverse(randomArr);
            System.arraycopy(randomArr, 0, res, randomArr.length, size / 2);
        } else {
            int[] randomArr = getRandomArr(size / 2 + 1, radical);
            System.arraycopy(randomArr, 0, res, 0, randomArr.length);
            reverse(randomArr);
            // 从翻转后的数组的1位置开始拷贝，拷贝的长度需要减一
            System.arraycopy(randomArr, 1, res, randomArr.length, size / 2);
        }
        return res;

    }

    // 获取随机数组
    public int[] getRandomArr(int size, int radical) {
        return Stream.generate(() -> (int) (Math.random() * Math.pow(10, radical)))
                .limit(size)
                .mapToInt(e -> e)
                .toArray();
    }

    // 数组翻转
    public void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    // 判断是否是回文数组
    public boolean ispalindromArray(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            if (arr[i] != arr[j]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test9() {
        int[] palindromArray = CommonUtil.getRandomArr(6, 3);
        System.out.println(Arrays.toString(palindromArray));
        ListNode listNode = generateNodeList(palindromArray);
        print(listNode);
        ListNode midOrUpPreNode = getMidOrDownPreNode(listNode);
        System.out.println(midOrUpPreNode.val);
    }

    public boolean isPalindrom(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head != null && head.next == null) {
            return true;
        }
        if (head != null && head.next != null && head.next.next == null) {
            return head.val == head.next.val;
        }
        ListNode slow = head;
        ListNode quick = head.next;
        ListNode pre = null;
        ListNode next = null;
        while (quick != null) {
            next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;

            if (quick.next != null && quick.next.next != null) {
                quick = quick.next.next;
            } else {
                quick = quick.next;
            }
        }
        while (pre != null && slow != null) {
            if (pre.val != slow.val) {
                return false;
            }
        }
        return true;
    }


    //  打印单链表
    public void print(ListNode head) {
        ListNode cur = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (cur != null) {
            stringBuilder.append(cur.val).append("、");
            cur = cur.next;
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder.toString());
    }

    public ListNode generateNodeList(int[] arr) {
        ListNode head = null;
        ListNode cur = null;
        for (int n : arr) {
            if (head == null) {
                head = new ListNode(n);
                cur = head;
            } else {
                cur.next = new ListNode(n);
                cur = cur.next;
            }
        }
        return head;
    }

    // 返回链表的中点（偶数个-前中点）
    public ListNode getMidOrDownPreNode(ListNode head) {
        // 没有节点 or 一个节点 or 两个节点
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        // 慢节点走一步
        ListNode slow = head;
        // 快节点走两步
        ListNode fast = head.next;
        while (fast != null && fast.next != null && fast.next.next != null) {
            // 慢节点走一步
            slow = slow.next;
            // 快节点走两步
            fast = fast.next.next;
        }
        return slow;
    }


    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    @Test
    public void test8() {
        int[] arr = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(arr).sum();
        System.out.println(sum);

        int[] randomArr = getRandomArr(5, 1);
        ListNode listNode = generateNodeList(arr);
        ListNode listNode1 = removeNthFromEnd(listNode, 2);
        print(listNode1);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = size(head);
        int step = size - n - 2;
        ListNode cur = head;
        while (step-- >= 0) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return head;


    }

    public int size(ListNode head) {
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            size++;
        }
        return size;
    }


    @Test
    public void test4() {

        int[] ints = distributeCandies(7, 4);
        System.out.println(Arrays.toString(ints));

    }


    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        // 发出去的糖果
        int out = 0;
        // 数组当前索引
        int index = 0;
        while (candies > 0) {
            // 待分发的糖果数大于剩余的糖果数，将所有的糖果分发给当前的孩子
            if (out + 1 > candies) {
                out = candies;
                candies = 0;
            } else {
                // 糖果充足，本次分发糖果在上一次的基础上加一
                out++;
            }
            // 当前孩子拥有的糖果数为之前的加上当前分发的
            ans[index % (num_people)] = ans[index % (num_people)] + out;
            // 分发后总糖果数减去当前分发的
            candies -= out;
            // 指针下移
            index++;
        }
        return ans;
    }
}
