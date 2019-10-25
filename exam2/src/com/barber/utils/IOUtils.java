package com.barber.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOUtils {
    private static IOUtils instance;
    private IOUtils(){}
    public static IOUtils getUtils(){
        if(instance == null){
            instance = new IOUtils();
        }
        return instance;
    }

    public boolean fileWrite(String resource,String absolutePath) throws IOException {
        BufferedWriter bw = null;
        boolean flag = false;
        try {
            bw = new BufferedWriter(new FileWriter(new File(absolutePath),true));
            bw.write(new String(resource.getBytes(), StandardCharsets.UTF_8));
            flag = true;
            bw.flush();
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}
