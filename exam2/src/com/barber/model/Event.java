package com.barber.model;

/**
 * 事件类
 */
public class Event implements Comparable<Event> {
    private int id;

    private Time arriveTime;//到达时间
    private Time shaveTime;//剃头时间
    private Time startShave;
    private Time leaveTime;//离开时间
    private Time stayTime;//停留时间

    public Event(int id, Time arriveTime, int shaveTime) {
        this.arriveTime = new Time(arriveTime);
        this.shaveTime = new Time(shaveTime);
        this.id = id;
    }

    public Event(Event event) {
        this.id = event.id;
        this.arriveTime = event.arriveTime == null ? null : new Time(event.arriveTime);
        this.shaveTime = event.shaveTime == null ? null : new Time(event.shaveTime);
        this.startShave = event.startShave == null ? null : new Time(event.startShave);
        this.leaveTime = event.leaveTime == null ? null : new Time(event.leaveTime);
        this.stayTime = event.stayTime == null ? null : new Time(event.stayTime);
    }

    public int getId() {
        return id;
    }

    public Time getStayTime() {
        return stayTime;
    }

    public void setStayTime(Time stayTime) {
        this.stayTime = stayTime;
    }

    public Time getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Time arriveTime) {
        this.arriveTime = new Time(arriveTime);
    }

    public Time getShaveTime() {
        return shaveTime;
    }

    public void setShaveTime(Time shaveTime) {
        this.shaveTime = new Time(shaveTime);
    }

    public Time getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Time leaveTime) {
        this.leaveTime = new Time(leaveTime);
        this.stayTime = this.arriveTime.interval(this.leaveTime);
    }

    public Time getStartShave() {
        return startShave;
    }

    public void setStartShave(Time startShave) {
        this.startShave = new Time(startShave);
    }

    /**
     * 比较arriveTime
     *
     * @param event
     * @return
     */
    @Override
    public int compareTo(Event event) {
        return arriveTime.compareTo(event.arriveTime);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", arriveTime=" + arriveTime +
                ", shaveTime=" + shaveTime +
                ", startShave=" + startShave +
                ", leaveTime=" + leaveTime +
                ", stayTime=" + stayTime +
                '}';
    }
}
