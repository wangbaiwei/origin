import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Demo1 {


    public void removeRepNode(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        Set<Integer> set = new HashSet<>();
        ListNode pre = head;
        ListNode cur = pre.next;
        set.add(pre.val);
        while (cur != null) {
            if (set.contains(cur.val)) {
                pre.next = cur.next;
                cur = pre.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
            set.add(pre.val); // 此处添加pre.val
        }
    }

    @Test
    public void test() {
        ListNode listNode = generateNodeList(new int[]{5, 5, 1, 1, 2, 2, 3, 3, 3, 4, 2, 5});
        removeRepNode(listNode);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddHead = head.next;
        ListNode evenHead = head;

        ListNode curOdd = oddHead;
        ListNode curEven = evenHead;

        boolean flag = true;
        while (curEven != null && curOdd != null) {
            if (flag) {
                if (curEven != null && curEven.next != null) {
                    curEven.next = curEven.next.next;
                    curEven = curEven.next;
                }
                flag = false;
            } else {
                if (curOdd != null && curOdd.next != null) {
                    curOdd.next = curOdd.next.next;
                    curOdd = curOdd.next;
                }
                flag = true;
            }
        }
        curEven.next = oddHead;
        return evenHead;
    }


    public ListNode removeElements(ListNode head, int val) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(-1, head);
        ListNode cur = pre;

        while (cur != null) {
            if (cur.next != null && cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }

        }

        return pre.next;

    }


    public ListNode generateNodeList(int[] arr) {
        ListNode head = null;
        ListNode cur = head;
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


    class ListNode {
        int val;
        ListNode next;

        public ListNode(int value) {
            this.val = value;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
