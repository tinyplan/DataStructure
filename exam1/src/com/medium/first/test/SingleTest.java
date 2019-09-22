package com.medium.first.test;

import com.medium.first.model.SingleList;

public class SingleTest {
    public static void main(String[] args) {
        SingleList<String> list = new SingleList<>();
        list.insert(1,"1");
        list.insert(2,"2");
        list.insert(3,"3");
        System.out.println(list.toString());
        SingleList<String> list1 = new SingleList<>(list);
        list1.remove("2");
        System.out.println(list1.toString());
    }
}
