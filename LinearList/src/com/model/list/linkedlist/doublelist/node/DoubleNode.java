package com.model.list.linkedlist.doublelist.node;

/**
 * 双链表结点
 * @param <T>
 */
public class DoubleNode<T> {
    public T data;//数据域
    public DoubleNode<T> prev;//指向前驱结点
    public DoubleNode<T> next;//指向后继节点

    public DoubleNode(T data) {
        this(data,null,null);
    }

    public DoubleNode(T data, DoubleNode<T> prev, DoubleNode<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public DoubleNode() {
        this(null,null,null);
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
