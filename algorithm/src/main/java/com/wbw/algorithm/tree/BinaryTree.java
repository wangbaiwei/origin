package com.wbw.algorithm.tree;

public class BinaryTree {


    public Info process(Node head) {

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        boolean isBinarySearchTree = true;

        if (leftInfo != null && leftInfo.flag == false) {
            isBinarySearchTree = false;
        }
        if (rightInfo != null && rightInfo.flag == false) {
            isBinarySearchTree = false;
        }
        if (rightInfo != null && rightInfo.min < head.val) {
            isBinarySearchTree = false;

        }

        if (leftInfo != null && leftInfo.max > head.val) {
            isBinarySearchTree = false;
        }

        int max = Math.max(leftInfo.max, rightInfo.max);
        int min = Math.min(leftInfo.min, rightInfo.min);
        return new Info(isBinarySearchTree, min, max);

    }


    class Info {
        boolean flag;
        int min;
        int max;

        public Info(boolean flag, int min, int max) {
            this.flag = flag;
            this.min = min;
            this.max = max;
        }
    }


    class Node {
        int val;
        Node left;
        Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
