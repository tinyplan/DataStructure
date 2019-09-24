package com.hard.model;

import com.medium.first.model.Node;
import com.sun.xml.internal.txw2.output.IndentingXMLFilter;

/**
 * 循环双链表类
 * @param <T>
 */
public class CirDoubleList<T> {
    public DoubleNode<T> head;
    private int size;

    //三种构造方法
    public CirDoubleList() {
        this.head = new DoubleNode<>();//创建头结点,三个域均为null
        //均指向自身
        this.head.prev = this.head;
        this.head.next = this.head;
        this.size = 0;
    }

    public CirDoubleList(T[] values){
        this();
        DoubleNode<T> rear = this.head;
        for(T value : values){
            rear.next = new DoubleNode<>(value,rear,this.head);
            this.head.prev = rear.next;
            size++;
            rear = rear.next;
        }
    }

    public CirDoubleList(CirDoubleList<T> list){
        this();
        this.size = list.size;
        DoubleNode[] nodes = new DoubleNode[size];
        DoubleNode<T> p = list.head.next;
        int i = 0;
        while (p != list.head){
            nodes[i] = new DoubleNode<>(p.data);
            p = p.next;
            i++;
        }
        //赋予当前list
        DoubleNode<T> target = this.head;
        for(i = 0; i < size; i++){
            target.next = nodes[i];
            nodes[i].prev = target;
            target = target.next;
        }
        //连接首尾结点
        this.head.prev = nodes[size-1];
        nodes[size-1].next = this.head;
    }

    public boolean isEmpty(){
        return this.head.next == this.head;
    }

    /**
     * 获取结点
     * @param index
     * @param request 操作数: -1、0、1 分别表示 前驱，当前，后继结点
     * @return
     */
    private DoubleNode<T> getNode(int index,int request){
        //判断操作
        switch (index){
            case -1:
                index--;
                break;
            case 1:
                index++;
                break;
        }
        DoubleNode<T> target = this.head;//初始front指向头结点
        //语句说明:当target.next指向头结点时,说明到达尾结点
        //语句作用:使target指向index位置的前驱元素
        for(int i = 0; target.next != this.head && i <= index; i++) target = target.next;
        return target;
    }

    public T get(int index){
        DoubleNode<T> target = this.getNode(index,0);
        return target.data;
    }

    public void set(int index,T data){

    }

    /**
     * 插入元素
     * @param index 需要插入元素的索引
     * @param data 插入的数据
     * @return 包装后的结点
     */
    public DoubleNode<T> insert(int index,T data){
        if(data == null) throw new NullPointerException("插入数据为空");
        DoubleNode<T> front = this.getNode(index,-1);
        DoubleNode<T> insertNode = new DoubleNode<>(data,front,front.next);
        //指向状态改变
        front.next.prev = insertNode;
        front.next = insertNode;
        size++;
        return insertNode;
    }

    /**
     * 插入元素
     * 未指定插入索引,直接尾插入
     * @param data 要插入的数据
     * @return
     */
    public DoubleNode<T> insert(T data){
        if(data == null) throw new NullPointerException("插入数据为空");
        /**语句说明:
         *  尾插入
         *  要插入元素的前驱元素为尾结点,后继节点为头结点
         */
        DoubleNode<T> insertNode = new DoubleNode<>(data,this.head.prev,this.head);
        //指向状态改变
        this.head.prev.next = insertNode;
        this.head.prev = insertNode;
        size++;
        return insertNode;
    }

    public T remove(int index){
        if(isEmpty() || index >= size) return null;
        DoubleNode<T> cur = this.getNode(index,0);
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        size--;
        return cur.data;
    }

    public T removeLast(){
        DoubleNode<T> rear = this.head.prev;
        this.head.prev = rear.prev;
        rear.prev.next = this.head;
        size--;
        return rear.data;
    }

    public void clear(){
        this.head.next = this.head;
        this.head.prev = this.head;
        this.size = 0;
    }

    public int size(){
        return this.size;
    }

    @Override
    public String toString(){
        DoubleNode<T> p = this.head.next;
        StringBuilder str = new StringBuilder(this.getClass().getName()).append("(");
        while(p != this.head){
            str.append(p.data.toString());
            if(p != this.head.prev) str.append(",");
            p = p.next;
        }
        return str.append(")").toString();
    }
}
