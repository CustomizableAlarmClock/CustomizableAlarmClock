package com.example.derek.customizablealarmclock;

import java.util.ArrayList;

/**
 * Created by Derek on 4/26/2018.
 *
 * The Alarm object contains information about each alarm create by the user.
 */

public class Alarm {

    private String alarmName;
    private ArrayList<Sound> sounds;
    private AlarmTime time;
    private long timeLeft;
    private int repeat;
    private boolean isActive;
    private boolean snoozeActive;
    private int id;

    //create an Alarm object
    public Alarm(String alarmName, ArrayList<Sound> sounds, AlarmTime time, long timeLeft, int repeat, boolean isActive, boolean snoozeActive, int id){
        this.alarmName = alarmName;
        this.sounds = sounds;
        this.time = time;
        this.timeLeft = timeLeft;
        this.repeat = repeat;
        this.isActive = isActive;
        this.snoozeActive = snoozeActive;
        this.id = id;
    }

    public String getAlarmName(){
        return alarmName;
    }

    public ArrayList<Sound> getSounds(){
        return sounds;
    }

    public AlarmTime getTime(){
        return time;
    }

    public long getTimeLeft(){
        return timeLeft;
    }

    public int getRepeat(){
        return repeat;
    }

    public boolean getIsActive(){
        return isActive;
    }

    public boolean getSnoozeActive(){
        return snoozeActive;
    }

    public int getId(){
        return id;
    }

    public void setAlarmName(String newName){

        alarmName = newName;
    }

    public void setRepeat(int repeat){
        this.repeat = repeat;
    }

    public void setTime(AlarmTime time){
        this.time = time;
    }

    public void setTimeLeft(long timeLeft){
        this.timeLeft = timeLeft;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }

    public void setSnoozeActive(boolean snoozeActive){
        this.snoozeActive = snoozeActive;
    }

    public void setId(int id){
        this.id = id;
    }
}
