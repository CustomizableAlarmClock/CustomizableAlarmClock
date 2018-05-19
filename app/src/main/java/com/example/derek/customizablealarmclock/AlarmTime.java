package com.example.derek.customizablealarmclock;

import java.util.Calendar;

/**
 * Created by Derek on 4/30/2018.
 * Creates an object of the time when the Alarm will go off
 */
public class AlarmTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    /**
     * Constructor for the AlarmTime object
     * @param year the year the Alarm goes off
     * @param month the month the Alarm goes off
     * @param day the day of the month the Alarm goes off
     * @param hour the hour the Alarm goes off
     * @param minute the minute the Alarm goes off
     */
    AlarmTime(int year, int month, int day, int hour, int minute){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * The default constructor for the AlarmTime object
     * Sets the Alarm to go off one hour after the current time
     */
    AlarmTime(){
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        if(c.get(Calendar.HOUR_OF_DAY)==23) {
            hour = 0;
        }
        else {
            hour = c.get(Calendar.HOUR_OF_DAY);
        }
        minute = c.get(Calendar.MINUTE);
    }

    /**
     * Gets the year the Alarm will go off
     * @return the year the Alarm will go off
     */
    public int getYear(){
        return year;
    }

    /**
     * Gets the month the Alarm will go off
     * @return the month the Alarm will go off
     */
    public int getMonth(){
        return month;
    }

    /**
     * Gets the day of the month the Alarm will go off
     * @return the day of the month the Alarm will go off
     */
    public int getDay(){
        return day;
    }

    /**
     * Gets the hour the Alarm will go off
     * @return the hour the Alarm will go off
     */
    public int getHour(){
        return hour;
    }

    /**
     * Gets the minute the Alarm will go off
     * @return the minute the Alarm will go off
     */
    public int getMinute(){
        return minute;
    }
}