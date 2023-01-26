package com.csii.wbw;

import java.util.LinkedList;
import java.util.Queue;

public class QueueSplicingStack<T> {

    private Queue<T> queue;
    private Queue<T> help;

    public QueueSplicingStack () {
        queue = new LinkedList<>();
        help = new LinkedList<>();
    }
    public void push (T t) {
        queue.offer(t);
    }

    public T poll () {
        if (queue.isEmpty() && help.isEmpty()) {
            throw new RuntimeException("deque is empty");
        }

        while (queue.size() > 1) {
            help.offer(queue.poll());
        }

        T ans = queue.poll();
        Queue<T> tmp = queue;
        queue = help;
        help = tmp;
       return ans;
    }
}
