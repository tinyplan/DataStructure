package com.hard.model;

public class SortCirDoubleList<T extends Comparable<? super T>> extends CirDoubleList<T> {

    public SortCirDoubleList() {
        super();
    }

    public SortCirDoubleList(T[] values) {
        super();
        for (T value : values) this.insert(value);
    }

    public SortCirDoubleList(CirDoubleList<T> list) {
        super(list);
    }

    public SortCirDoubleList(SortCirDoubleList<T> list){
        super();
        for (DoubleNode<T> p = list.head.next; p != null; p = p.next) this.insert(p.data);
    }

    //不支持该方法
    @Override
    public void set(int index, T data) {
        throw new UnsupportedOperationException("Unsupported Method set(index,data)");
    }

    //不支持该方法
    @Override
    public DoubleNode<T> insert(int index, T data) {
        throw new UnsupportedOperationException("Unsupported Method insert(i,data)");
    }

    @Override
    public DoubleNode<T> insert(T data) {
        if(this.isEmpty() || data.compareTo(this.head.prev.data) > 0)
            return super.insert(data);//若为空表,或比尾结点大,直接尾插入
        DoubleNode<T> rear = this.head.next;
        while (rear != head && data.compareTo(rear.data) > 0) rear = rear.next;
        DoubleNode<T> target = new DoubleNode<>(data,rear.prev,rear);
        rear.prev.next = target;
        rear.prev = target;
        size++;
        return target;
    }

    @Override
    public DoubleNode<T> insertDifferent(T data) {
        if(super.search(data) != null) return null;
        return this.insert(data);
    }

    @Override
    public void addAll(CirDoubleList<T> list) {
        this.size+=list.size;
        DoubleNode<T> p = list.head.next;//指向插入链表头结点
        while(p != list.head){
            this.insert(p.data);
            p = p.next;
        }
    }
}
