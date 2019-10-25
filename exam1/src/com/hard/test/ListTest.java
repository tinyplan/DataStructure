package com.hard.test;

import com.hard.model.CirDoubleList;
import com.hard.model.SortCirDoubleList;

public class ListTest {
    public static void main(String[] args) {
        Integer[] str = new Integer[]{1,4,3};
        Integer[] str1 = new Integer[]{5,34,6};
        CirDoubleList<Integer> list = new SortCirDoubleList<>(str);
        CirDoubleList<Integer> list1 = new SortCirDoubleList<>(str1);
        list.addAll(list1);
    }
    void print(CirDoubleList list){
        System.out.println(list.toString());
    }
}
