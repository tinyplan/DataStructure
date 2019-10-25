package com.hard.utils;

import com.hard.model.Book;
import com.hard.model.CirDoubleList;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONUtils {

    public JSONUtils() {}

    //获取JSON文件内容
    public String readJSONString(String path) throws Exception{
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //写入内容
    public void writeJSONString(String resource,String path) throws Exception{
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
            writer.write(resource);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读入后切割字符串,根据正则表达式
     * @param resource 字符串源
     * @param regex 正则表达式
     * @return  name->status
     */
    public String[] splitJSONString(String resource,String regex){
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(resource);
        while(matcher.find()){
            sb.append(matcher.group(1)+"->"+matcher.group(2)+",");
        }
        return sb.toString().split(",");
    }

    /**
     * 读取保存数据
     * @param resources 资源数组
     * @return
     */
    public CirDoubleList saveInList(String[] resources){
        CirDoubleList<Book> Lists = new CirDoubleList<>();
        Book book = null;
        for(String str:resources){
            String[] res = str.split("->");
            book = new Book("\""+res[0]+"\"",Integer.parseInt(res[1]));
            Lists.insert(book);
        }
        return Lists;
    }
}
