package com.csii.wbw;

import org.junit.jupiter.api.Test;
import java.util.Stack;
public class ValidParentheses {


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
