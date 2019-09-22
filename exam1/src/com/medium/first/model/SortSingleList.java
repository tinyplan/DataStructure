package com.medium.first.model;

public class SortSingleList<T extends Comparable<? super T>> extends SingleList<T> {

    public SortSingleList() {
        super();
    }

    public SortSingleList(T[] values) {
        super();
        for (T value : values) this.insert(value);
    }

    public SortSingleList(SortSingleList<T> list) {
        super(list);
    }

    public SortSingleList(SingleList<T> list){
        super();
        for (Node<T> p = list.head.next; p != null; p = p.next) this.insert(p.data);
    }

    //不支持该方法
    @Override
    public void set(int i,T data){
        throw new UnsupportedOperationException("Unsupported Method set(i,data)");
    }

    //不支持该方法
    @Override
    public Node<T> insert(int index, T data) {
        throw new UnsupportedOperationException("Unsupported Method insert(i,data)");
    }

    /**
     * 插入元素
     * @param data
     * @return 插入的结点
     */
    @Override
    public Node<T> insert(T data) {
        Node<T> front = this.head;
        Node<T> p = front.next;
        while(p != null && data.compareTo(p.data) > 0){//寻找第一个比data大的结点
            front = p;
            p = p.next;
        }
        //此时front为插入位置的前驱结点
        //    p为插入位置的后继节点
        front.next = new Node<>(data,p);
        return front.next;
    }

    @Override
    public Node<T> insertDifferent(T data) {
        return super.insertDifferent(data);
    }

    @Override
    public T remove(T key) {
        return super.remove(key);
    }

    @Override
    public Node<T> search(T key) {
        return super.search(key);
    }
}
