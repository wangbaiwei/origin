package com.wbw.algorithm.tree;

import com.wbw.algorithm.linkedList.ListNode;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

public class TreeMaxWidth {


    public int getMaxWidthOfTree(Node head) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 当前层的结束节点为head
        Node curEnd = head;
        // 下层的结束节点
        Node nextEnd = null;
        // 当前层节点个数统计
        int count = 0;
        // 层的最大节点数
        int max = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            count++;

            if (cur == curEnd) {
                curEnd = nextEnd;
                max = Math.max(count, max);
                count = 0;
                nextEnd = null;
            }
        }
        return max;
    }


    class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public int deleteGreatestValue(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            Arrays.sort(grid[i]);
        }

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 1; k < row; k++) {
                    if (grid[k][i] > grid[k - 1][i]) {
                        int tmp = grid[k][i];
                        grid[k][i] = grid[k - 1][i];
                        grid[k - 1][i] = tmp;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < col; i++) {
            ans += grid[0][i];
        }
        return ans;
    }

    @Test
    public void test() {
        process(1, 4, true);
    }

    public void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true);
        System.out.print(down ? "凹" : "凸"); // 凹凹凸凹凹凸凸凹凹凹凸凸凹凸凸
        process(i + 1, N, false);
    }


    @Test
    public void test10() {

        Set<Integer> set = new HashSet<>();
        System.out.println(set.add(1));
        System.out.println(set.add(1));
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println(strToInt("42"));


    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public int strToInt(String str) {


        int flag = 1;
        int index = 0;
        while (str.charAt(index++) == ' ') {
        }

        StringBuilder sb = new StringBuilder();
        while (index < str.length()) {
            char c = str.charAt(index++);
            if (c == '-') {
                flag = -1;
            } else if (c < '0' || c > '9') {
                break;
            } else {
                sb.append(c);
            }
        }
        int ans = 0;
        int len = sb.length();
        char[] arr = sb.toString().toCharArray();
        for (int i = len - 1; i >= 0; i--) {
            int v = arr[i] - '0';
            double vv = v * Math.pow(10, len - i - 1);
            if (ans + vv > Integer.MAX_VALUE) {
                return -1;
            } else {
                ans += vv;
            }
        }
        return ans * flag;


    }

    public int maxSubBSTSize2(Node node) {
        if (node == null) {
            return 0;
        }
        return process(node).maxBSTSubtreeSize;
    }

    public Info process(Node node) {

        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int maxBSTSubtreeSize;
        int allSize = node.val;
        int max = node.val;
        int min = node.val;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            allSize += rightInfo.allSize;
        }
        // 单独x左数上的最大搜索二叉树
        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;

        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if (rightBST && leftBST) {
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < node.val);
            boolean rightMinMoreX = rightInfo == null ? true : (node.val < rightInfo.min);
            if (leftMaxLessX && rightMinMoreX) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
    }


    class Info {

        public int maxBSTSubtreeSize;
        public int allSize;
        public int min;
        public int max;

        public Info(int maxBSTSubtreeSize, int allSize, int min, int max) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.allSize = allSize;
            this.min = min;
            this.max = max;
        }
    }

    @Test
    public void testHello() {
        System.out.println(nextGreaterElement(56421));
    }

    public int nextGreaterElement(int n) {

        int ans = 0;
        int index = 0;
        int tmp = 0;
        boolean flag = false;
        while (n > 0) {
            if (flag) {

                ans += tmp * Math.pow(10, index);
                index++;
                n /= 10;
                tmp = n % 10;


            } else {
                int a = n % 10;
                int b = n / 10 % 10;
                if (n / 10 != 0 && a > b) {
                    tmp = a;
                    ans += b * Math.pow(10, index);
                    flag = true;
                } else {
                    ans += a * Math.pow(10, index);
                }
                index++;
                n /= 10;
            }

        }

        return flag ? ans : -1;
    }


}
