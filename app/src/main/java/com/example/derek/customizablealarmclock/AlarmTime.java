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

    //Constructor for the AlarmTime
    public AlarmTime(int year, int month, int day, int hour, int minute){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    //default Constructor for the AlarmTime - 1 hour after the current time
    public AlarmTime(){
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

    //gets the year that the alarm is to go off at
    public int getYear(){
        return year;
    }

    //gets the month that the alarm is to go off at
    public int getMonth(){
        return month;
    }

    //gets the day that the alarm is to go off at
    public int getDay(){
        return day;
    }

    //gets the hour that the alarm is to go off at
    public int getHour(){
        return hour;
    }

    //returns the minute that the alarm is to go off at
    public int getMinute(){
        return minute;
    }
}
