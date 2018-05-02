package com.example.derek.customizablealarmclock;

import java.util.Calendar;

/**
 * Created by Derek on 4/30/2018.
 *
 * Time that an alarm will go off
 */

public class AlarmTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    AlarmTime(int year, int month, int day, int hour, int minute){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    AlarmTime(){
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        if(c.get(Calendar.HOUR_OF_DAY)==23) {
            hour = 0;
        }
        else{
            hour = c.get(Calendar.HOUR_OF_DAY);
        }
        minute = c.get(Calendar.MINUTE);

    }

    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public void setDay(int day){
        this.day = day;
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }
}
