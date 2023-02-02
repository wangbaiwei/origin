package com.csii.wbw;

import java.util.Stack;

public class StackSplicingQueue {

    private Stack<Integer> stackPush = new Stack<>();
    private Stack<Integer> stackPop = new Stack<>();

    public void add(int num) {
        stackPush.push(num);
        reciprocalData();
    }

    public int poll() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        reciprocalData();
        return stackPop.pop();
    }

    /**
     * 倒数据
     * 一次性将数据倒完
     * 只有在push栈为空是，才可以倒数据
     */
    public void reciprocalData() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }
}
