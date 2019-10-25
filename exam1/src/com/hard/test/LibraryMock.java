package com.hard.test;

import com.hard.model.Book;
import com.hard.model.CirDoubleList;
import com.hard.model.DoubleNode;
import com.hard.utils.JSONUtils;

import java.util.Scanner;

/**
 * 图书馆模拟
 * 思路:现将源数读出,关闭时将list中的数据写回JSON文件
 */
public class LibraryMock {
    private static final String path = "D:\\IdeaProjects\\DataStructure\\exam1\\src\\com\\hard\\config\\Library.json";
    private static final String regex = "\"name\": \"(.*?)\", \"status\": (1|0)";
    private static CirDoubleList<Book> bookList = null;
    private static char cmd ;
    private static JSONUtils ju = null;
    private static Scanner kb = null;

    public static void main(String[] args) throws Exception{
        kb = new Scanner(System.in);
        ju = new JSONUtils();
        System.out.println("读取数据中...");
        bookList = ju.saveInList(ju.splitJSONString(ju.readJSONString(path),regex));
        System.out.println("数据读取完成");
        System.out.println("指令列表:添加新书(A)\n\t\t借出图书(B)\n\t\t归还图书(R)\n\t\t显示图书馆的当前状态(S)\n\t\t退出程序(Q)");
        while(true){
            System.out.print("请输入指令:");
            cmd = kb.nextLine().charAt(0);
            //大小写转换
            cmd = cmd >= 'a' && cmd <= 'z' ? (char)((int)cmd - 32) : cmd;
            switch (cmd){
                case 'A':
                    addBook();
                    break;
                case 'B':
                    borrowBook();
                    break;
                case 'R':
                    returnBook();
                    break;
                case 'S':
                    showAll();
                    break;
                case 'Q':
                    quit();
                    break;
                default:
                    System.out.println("指令非法！请重新输入");
                    break;
            }
        }
    }

    private static void addBook() {
        try {
            System.out.print("请输入书名:");
            String name = kb.nextLine().trim();
            Book newBook = new Book("\""+name+"\"",1);
            System.out.print("\t书名:" + name);
            DoubleNode<Book> node = bookList.insertDifferent(newBook);
            if (node != null) {
                System.out.println("\t添加成功!");
                System.out.println(bookList);
            } else {
                System.out.println("\t此书已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void borrowBook() {
        try {
            System.out.print("请输入书名:");
            String name = "\""+kb.nextLine().trim()+"\"";
            Book searchBook = new Book(name);
            DoubleNode<Book> node = bookList.search(searchBook);
            switch (changeStatus(node,0)){
                case -1:
                    System.out.println("\t查无此书,请确认书名!");
                    break;
                case 0:
                    System.out.println("\t此书已借出!\n\t借书失败");
                    break;
                case 1:
                    System.out.println("\t借书成功!");
                    break;
                default:
                    System.out.println("未知错误");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void returnBook() {
        try {
            System.out.print("请输入书名:");
            String name = "\""+kb.nextLine().trim()+"\"";
            Book searchBook = new Book(name);
            DoubleNode<Book> node = bookList.search(searchBook);
            switch (changeStatus(node,1)){
                case -1:
                    System.out.println("\t查无此书,请确认书名!");
                    break;
                case 0:
                    System.out.println("\t此书未借出!\n\t还书失败");
                    break;
                case 1:
                    System.out.println("\t还书成功!");
                    break;
                default:
                    System.out.println("未知错误");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showAll() {
        String[] books = ju.splitJSONString(bookList.toString(),regex);
        System.out.println("\t书名\t\t状态");
        for(String book:books){
            String[] res = book.split("->");
            res[1] = res[1].equals("1") ? "在架" : "借出";
            System.out.println("\t" + res[0] + "\t" + res[1]);
        }
    }

    private static void quit() throws Exception {
        System.out.println("正在保存数据...");
        ju.writeJSONString(bookList.toString(),path);
        System.out.println("退出程序...");
        System.exit(0);
    }

    /**
     *返回状态码
     * @param node
     * @param changedStatus
     * @return -1 null;
     *          0 fail;
     *          1 success
     */
    private static int changeStatus(DoubleNode<Book> node,int changedStatus){
        if(node == null) {
            return -1;
        }
        //相反的状态
        int reverse = (changedStatus+1)%2;
        if(node.data.getStatus() == reverse){
            node.data.setStatus(changedStatus);
            return 1;
        } else{
            return 0;
        }
    }
}
