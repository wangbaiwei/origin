package com.wbw.algorithm.leetCode;


import java.util.PriorityQueue;

public class QuickSort {


    public int largestInteger(int num) {
        PriorityQueue<Integer> odd = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        PriorityQueue<Integer> even = new PriorityQueue<>(((o1, o2) -> o2 - o1));

        char[] chars = String.valueOf(num).toCharArray();

        for (char c : chars) {
            int t = c - '0';
            if (t % 2 == 1) {
                even.offer(t);
            } else {
                odd.offer(t);
            }
        }

        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            int t = c - '0';
            if (t % 2 == 1) {
                sb.append(even.poll());
            } else {
                sb.append(odd.poll());
            }
        }
        return Integer.parseInt(sb.toString());

    }

    public static int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {


        int[] nums = new int[] {2, 3,5,6,7};
        TreeNode helper = helper(nums, 0, nums.length - 1);
        print(helper);



    }
    public static void print(TreeNode helper) {
        if (helper == null) {
            return;
        }
        System.out.println(helper.val);
        print(helper.left);
        print(helper.right);

    }


    public static TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置右边的数字作为根节点
        int mid = (left + right + 1) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }


    public static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
}
