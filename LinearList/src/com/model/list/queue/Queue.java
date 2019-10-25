package com.model.list.queue;

public interface Queue<T> {
    boolean isEmpty();
    T peek();//返回队首元素，不出队
    boolean add(T data);//入队
    T poll();//出队
    int size();
}
