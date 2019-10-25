package com.model.list.stack;

public interface Stack<T> {
    boolean isEmpty();
    void push(T x);
    T pop();
    T peek();
}
