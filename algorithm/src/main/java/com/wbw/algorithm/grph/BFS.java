package com.wbw.algorithm.grph;

import java.util.*;

public class BFS {


    public void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.offer(start);
        set.add(start);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value);
            for (Node node : poll.nexts) {
                if (!set.contains(node)) {
                    queue.offer(node);
                    set.add(node);
                }
            }
        }
    }

    public void dfs(Node start) {
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(cur.value);
                    break;
                }
            }
        }
    }
}
