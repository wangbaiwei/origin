package com.csii.wbw;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class 有效的括号 {


    @Test
    public void test() {


        System.out.println(isValid("()"));
    }
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            switch (chr) {
                case '(':
                    stack.push(chr);
                    break;
                case '{':
                    stack.push(chr);
                    break;
                case '[':
                    stack.push(chr);
                    break;
                case ')':
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.peek() == ']') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }
}
