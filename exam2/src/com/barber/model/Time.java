package com.barber.model;

/**
 * 时间类
 * 以s(秒)为单位
 */
public class Time implements Comparable<Time>{
    private int hour;
    private int minute;
    private int second;

    public Time(int second) {
        this.format(second);
    }

    public Time(String date){
        String[] src = date.split(":");
        this.hour = Integer.parseInt(src[0]);
        this.minute = Integer.parseInt(src[1]);
        this.second = Integer.parseInt(src[2]);
    }

    public Time(Time time){
        this(time.transform());
    }

    /**
     * 转换方法
     * 从hh:mm:ss->s
     */
    public int transform() {
        return this.hour * 3600 + this.minute * 60 + this.second;
    }

    public void format(int second){
        this.hour = second / 3600;
        this.minute = (second % 3600) / 60;
        this.second = second % 60;
    }

    /**
     * 计算时间差
     * @param t
     * @return
     */
    public Time interval(Time t) {
        return new Time(Math.abs(this.transform() - t.transform()));
    }

    public void passTime(int second){
        this.format(this.transform()+second);
    }

    @Override
    public String toString() {
        return this.hour + ":" + this.minute + ":" + this.second;
    }

    @Override
    public int compareTo(Time time) {
        return Integer.compare(this.transform(), time.transform());
    }
}
