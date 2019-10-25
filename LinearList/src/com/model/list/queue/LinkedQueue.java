package com.model.list.queue;

import com.model.list.linkedlist.singlelist.node.Node;

public final class LinkedQueue<T> implements Queue<T> {
    private Node<T> front;//队头指针,第一个结点
    private Node<T> rear;//队尾结点
    private int size;

    public LinkedQueue() {
        this.front = this.rear = null;
    }

    @Override
    public boolean isEmpty() {
        return this.front == null && this.rear == null;
    }

    @Override
    public T peek() {
        return this.isEmpty() ? null : this.front.data;
    }

    @Override
    public boolean add(T data) {
        if (data == null) return false;
        Node<T> node = new Node<>(data, null);
        if (this.front == null) {
            this.front = node;
        }else {
            this.rear.next = node;
        }
        this.rear = node;
        size++;
        return true;
    }

    @Override
    public T poll() {
        if (isEmpty()) return null;
        T data = this.front.data;
        this.front = this.front.next;
        if (this.front == null)
            this.rear = null;
        size--;
        return data;
    }

    @Override
    public String toString() {
        return "[\n"+front.data.toString()+"\n]";
    }

    @Override
    public int size() {
        return this.size;
    }
}
