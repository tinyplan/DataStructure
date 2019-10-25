package com.barber.model;

import com.barber.utils.IOUtils;
import com.model.list.linkedlist.doublelist.CirDoubleList;

import java.io.IOException;

public class Log {
    private static final String logPath = "D:\\IdeaProjects\\DataStructure\\exam2\\src\\com\\log\\log.txt";
    private static final String listPath = "D:\\IdeaProjects\\DataStructure\\exam2\\src\\com\\log\\customerList.txt";
    private IOUtils ioutils;
    private StringBuilder sb;

    public Log(){
        sb = new StringBuilder("日志记录开始:\n");
        ioutils = IOUtils.getUtils();
    }

    public boolean writeLog(String resource, CirDoubleList list) throws IOException {
        sb.append(resource);
        return ioutils.fileWrite(sb.toString(),logPath) && ioutils.fileWrite(list.toString(),listPath);
    }

    public void addLog(String resource){
        sb.append(resource);
    }

    public void recordArrive(Time time,Event event){
        this.addLog("["+time.toString()+"]\tid="+event.getId()+"的顾客在["+event.getArriveTime()+"]到达\n");
    }

    public void recordWait(Time time,Event event){
        this.addLog("["+time.toString()+"]\tid="+event.getId()+"的顾客在["+event.getArriveTime()+"]等待理发\n");
    }

    public void recordShave(Time time,Event event){
        this.addLog("["+time.toString()+"]\tid="+event.getId()+"的顾客在["+event.getStartShave()+"]开始理发\n");
    }

    public void recordLeave(Time time,Event event){
        this.addLog("["+time.toString()+"]\tid="+event.getId()+"的顾客在["+event.getLeaveTime()+"]离开\n\t\t用户信息:"+event.toString()+"\n");
    }

}
