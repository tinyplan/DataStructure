package com.hard.model;

/**
 * 循环双链表类
 * @param <T>
 */
public class CirDoubleList<T> {
    public DoubleNode<T> head;
    public int size;

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
        DoubleNode<T> p = list.head.next;//指向插入链表头结点
        while(p != list.head){
            this.insert(p.data);
            p = p.next;
        }
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
        /*  语句说明:当target.next指向头结点时,说明到达尾结点
            语句作用:使target指向index位置的前驱元素*/
        for(int i = 0; target.next != this.head && i <= index; i++) target = target.next;
        return target;
    }

    @Deprecated
    public DoubleNode<T>[] getNodes(CirDoubleList<T> list){
        DoubleNode[] nodes = new DoubleNode[list.size];
        DoubleNode<T> p = list.head.next;
        int i = 0;
        while (p != list.head){
            nodes[i] = new DoubleNode<>(p.data);
            p = p.next;
            i++;
        }
        return nodes;
    }

    public T get(int index){
        DoubleNode<T> target = this.getNode(index,0);
        return target.data;
    }

    public void set(int index,T data){
        DoubleNode<T> cur = this.getNode(index,0);
        cur.data = data;
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

    /**
     * 插入不同的元素
     * @param data
     * @return
     */
    public DoubleNode<T> insertDifferent(T data){
        if(this.search(data) != null) return null;
        return insert(data);
    }

    /**
     * 合并两张链表
     * @param list
     */
    public void addAll(CirDoubleList<T> list){
        this.size+=list.size;
        DoubleNode<T> p = list.head.next;//指向插入链表头结点
        while(p != list.head){
            this.insert(p.data);
            p = p.next;
        }
    }

    /**
     * 删除元素
     * @param index 删除位置索引
     * @return
     */
    public T remove(int index){
        if(isEmpty() || index >= size) return null;
        DoubleNode<T> cur = this.getNode(index,0);
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        size--;
        return cur.data;
    }

    /**
     * 删除元素
     * @param key 删除位置的元素
     * @return
     */
    public T remove(T key){
        DoubleNode<T> front = this.search(key);
        if(front != null){
            front.prev.next = front.next;
            front.next.prev = front.prev;
            size--;
            return key;
        }
        return null;
    }

    public T removeLast(){
        DoubleNode<T> rear = this.head.prev;
        this.head.prev = rear.prev;
        rear.prev.next = this.head;
        size--;
        return rear.data;
    }

    public DoubleNode<T> search(T key){
        for(DoubleNode<T> p = this.head.next;p != this.head;p = p.next){
            if(key.equals(p.data)) return p;
        }
        return null;
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
        StringBuilder str = new StringBuilder().append("[\n");
        while(p != this.head){
            str.append(p.data.toString());
            if(p != this.head.prev) str.append(",\n");
            p = p.next;
        }
        return str.append("\n]").toString();
    }
}
