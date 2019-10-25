package com.hard.test;

import com.hard.model.Book;
import com.hard.model.CirDoubleList;
import com.hard.utils.JSONUtils;

/**
 * JSON测试类
 */
public class JSONTest {
    public static void main(String[] args) throws Exception {
        /*String path = JSONTest.class.getClassLoader().getResource("Library.json").getPath();*/
        String path = "D:\\IdeaProjects\\DataStructure\\exam1\\src\\com\\hard\\config\\Library.json";
        JSONUtils ju = new JSONUtils();
        String regex = "\"name\": \"(.*?)\", \"status\": (1|0)";
        String resource = ju.readJSONString(path);
        System.out.println(resource);
        String[] strs = ju.splitJSONString(resource,regex);
        for (String str: strs) {
            System.out.println(str);
        }
        /*CirDoubleList bookLists = ju.saveInList(strs);

        bookLists.insert(new Book("456789",0));
        System.out.println(bookLists.toString());
        ju.writeJSONString(bookLists.toString(),path);*/
    }
}
