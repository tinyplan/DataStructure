package com.model.list.stack;

import com.model.list.seqlist.SeqList;

/**
 * 顺序栈
 * @param <T>
 */
public final class SeqStack<T> implements Stack<T>{
    private SeqList<T> list;

    public SeqStack(int length){
        this.list = new SeqList<>(length);
    }

    public SeqStack() {
        this(64);
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    //压栈
    @Override
    public void push(T x) {
        this.list.insert(x);
    }

    //出栈
    @Override
    public T pop() {
        return this.list.remove(list.size()-1);
    }

    //寻址
    @Override
    public T peek() {
        return this.list.get(list.size()-1);
    }

    @Override
    public String toString() {
        return "SeqStack{" +
                "list=" + list +
                '}';
    }
}
