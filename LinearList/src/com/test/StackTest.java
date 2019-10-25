package com.test;

import com.model.list.stack.SeqStack;

public class StackTest {
    public static void main(String[] args) {
        SeqStack<Integer> stack = new SeqStack<>(5);
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(4);
        stack.push(3);
        stack.pop();
        stack.push(9);
        System.out.println(stack.peek());
        System.out.println(stack);
    }
}
