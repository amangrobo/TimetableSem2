package com.grobo.timetablesem2;

public class SingleDay {

    private String time;
    private String subject;

    public SingleDay(){

    }

    public SingleDay(String time, String subject) {
        this.time = time;
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
