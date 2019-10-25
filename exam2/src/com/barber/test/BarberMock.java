package com.barber.test;

import com.barber.model.Event;
import com.barber.model.Log;
import com.barber.model.Time;
import com.model.list.linkedlist.doublelist.CirDoubleList;
import com.model.list.linkedlist.doublelist.SortCirDoubleList;
import com.model.list.queue.LinkedQueue;
import com.model.list.queue.Queue;

import java.io.IOException;
import java.util.Random;

public class BarberMock {
    /*变量定义*/
    private static int chairNum = 2;//椅子数量
    private static Time openTime = null;//开店时间
    private static Time closeTime = null;//休业时间
    private static Time currentTime = null;//当前时间
    private static SortCirDoubleList<Event> eventList = null;
    private static Queue<Event> waitQueue = null;//等待队列
    private static SortCirDoubleList<Event> chair = null;//模拟椅子
    private static CirDoubleList<Event> logList = null;
    private static int totalStayTime = 0;
    private static int totalWaitNum;
    private static int totalEmptyChair;
    private static Log log = null;

    public static void main(String[] args) throws IOException {
        log = new Log();
        /*初始化*/
        init();
        double interval = (openTime.interval(closeTime)).transform() / 60.0;
        System.out.println(currentTime);

        log.addLog(currentTime.toString()+"\t理发店开始营业\n");
        ///////////////////主循环开始///////////////////
        while (currentTime.compareTo(closeTime) < 0 || !chair.isEmpty()) {
            checkEventList();
            checkWaitingQueue();
            checkChair();

            calculate();

            currentTime.passTime(60);
        }
        ///////////////////主循环结束///////////////////

        /*System.out.println("eventList="+eventList);
        System.out.println("logList"+logList);
        System.out.println("waitQueue="+(waitQueue.isEmpty()?"null":waitQueue));*/
        System.out.println(currentTime);
        log.addLog(currentTime.toString()+"\t理发店结束营业\n");
        boolean flag = log.writeLog("日志记录结束",logList);
        System.out.println(flag ? "日志输出成功" : "日志生成错误");

        for (int i = 0; i < logList.size(); i++) {
            totalStayTime += (logList.get(i).getStayTime().transform() / 60);
        }

        System.out.println("顾客总人数:" + logList.size());
        System.out.println("平均逗留时间:" + String.format("%.3f", 1.0 * totalStayTime / logList.size()) + "min");
        System.out.println("排队等候理发的平均人数:" + String.format("%.3f", totalWaitNum / interval));
        System.out.println("空椅子的平均数:" + String.format("%.3f", totalEmptyChair / interval));
    }

    private static void calculate() {
        totalWaitNum += waitQueue.size();
        totalEmptyChair += (chairNum - chair.size());
    }

    /////////////////////主方法区,用于处理模拟进行时期的逻辑判断////////////////////
    //检查是否有剃头完成
    private static void checkChair() {
        for (int i = 0; i < chair.size(); i++) {
            Event event = chair.get(i);
            if (currentTime.interval(event.getStartShave()).compareTo(event.getShaveTime()) == 0) {
                event.setLeaveTime(currentTime);
                logList.insert(new Event(event));
                chair.remove(i);
                log.recordLeave(currentTime,event);
                i--;//指针回退
            }
        }
    }

    //检查等待的人中是否有可以开始剃头
    private static void checkWaitingQueue() {
        Event event;
        while (!waitQueue.isEmpty() && hasEmptyChair()) {
            event = new Event(waitQueue.poll());
            event.setStartShave(currentTime);
            chair.insert(event);
            log.recordShave(currentTime,event);
        }
    }

    //检查是否有顾客到来，并进行判断
    private static void checkEventList() {
        Event event;
        if (!eventList.isEmpty()) {
            event = new Event(eventList.get(0));
            if (currentTime.compareTo(event.getArriveTime()) == 0) {//模拟顾客到达
                log.recordArrive(currentTime,event);
                if (waitQueue.isEmpty() && hasEmptyChair()) {//无人等待，有空余，直接开始剃头
                    event.setStartShave(currentTime);
                    chair.insert(event);
                    log.recordShave(currentTime,event);
                } else {//否则加入等待队列
                    waitQueue.add(event);
                    log.recordWait(currentTime,event);
                }
                eventList.remove(0);//移出
            }
        }
    }

    /////////////////////辅助方法区////////////////////
    //是否有空椅
    private static boolean hasEmptyChair() {
        return chair.size() < chairNum;
    }

    private static void init() {
        chair = new SortCirDoubleList<>();
        eventList = new SortCirDoubleList<>();
        waitQueue = new LinkedQueue<>();
        logList = new CirDoubleList<>();
        openTime = new Time("08:00:00");
        closeTime = new Time("20:00:00");
        currentTime = new Time(openTime);//初始化时，将当前时间赋值为开店时间
        initEventList();//初始化列表
    }

    //初始化事件
    private static void initEventList() {
        Random random = new Random();
        int arriveInterval = 60 * (10 + random.nextInt(20));//到达间隔
        int shaveTime;//理发时间
        Time current = new Time(openTime);//局部变量，代表当前时间，防止currentTime变化
        current.passTime(arriveInterval);
        Event event;
        int i = 1;
        while (current.compareTo(closeTime) < 0) {
            shaveTime = 20 + random.nextInt(40);//理发时间20~60
            event = new Event(i,current, shaveTime * 60);
            eventList.insert(event);

            current.passTime(arriveInterval);
            arriveInterval = 60 * (10 + random.nextInt(20));
            i++;
        }
        /*for (int i = 0; i < 15 && current.compareTo(closeTime) < 0; i++) {
            shaveTime = 45 + random.nextInt(60);
            event = new Event(current, shaveTime * 60);
            eventList.insert(event);

            current.passTime(arriveInterval);
            arriveInterval = 60 * (1+random.nextInt(60));
        }*/
    }
}
