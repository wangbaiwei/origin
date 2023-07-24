import com.wbw.algorithm.linkedList.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Demo {

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 3, 2, 1};
        Node head = generateNodeList(arr);
        System.out.println(judge(head));

    }

    public boolean judge(Node head) {
        Stack<Node> stack = new Stack<>();
        if (head == null) {
            return true;
        }
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        cur = head;
        while (!stack.isEmpty() && cur != null) {
            if (stack.pop().val != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }


    public Node generateNodeList(int[] arr) {
        Node head = null;
        Node cur = head;
        for (int n : arr) {
            if (cur == null) {
                cur = new Node(n);
                head = cur;
            } else {
                cur.next = new Node(n);
                cur = cur.next;
            }
        }
        return head;
    }

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        boolean nStraightHand = isNStraightHand(arr, 3);
        System.out.println(nStraightHand);
    }

    public boolean isNStraightHand(int[] hand, int groupSize) {
        int len = hand.length;
        if (len % groupSize != 0) {
            return false;
        }


        Arrays.sort(hand);
        if (len == groupSize) {
            int start = hand[0];
            for (int i = 1; i < len; i++) {
                if (hand[i] != ++start) {
                    return false;
                }
            }
        }

        int l = 0;
        int r = l + groupSize - 1;
        int diff = hand[r] - hand[l];
        for (int i = 0; i < len; i++) {
            l += groupSize;
            r += groupSize;
            if (hand[r] - hand[l] != diff) {
                return false;
            }
        }

        return true;
    }


    public boolean lemonadeChange(int[] bills) {

        int len = bills.length;
        int[] residue = new int[len];

        for (int i = 0; i < len; i++) {
            if (i > 0) {
                residue[i] = residue[i - 1];
            }
            if (bills[i] - 5 > residue[i]) {
                return false;
            }
            residue[i] += bills[i] - 5;
        }
        return true;

    }

    @Test
    public void test3() {


    }


    public int[] maxSlidingWindow(int[] nums, int k) {

        int len = nums.length;
        List<Integer> list = new ArrayList<>();
        int index = 0;
        int[] ans = new int[len - k + 1];
        for (int i = 0; i < len; i++) {
            if (i > k) {
                list.remove(nums[i - k]);
            }
            if (i >= k - 1) {
                ans[index++] = list.stream().sorted().max((a, b) -> b - a).get();
            }
            list.add(nums[i]);

        }

        return ans;

    }



}
