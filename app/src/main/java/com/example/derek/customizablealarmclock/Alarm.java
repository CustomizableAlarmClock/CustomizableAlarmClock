package com.example.derek.customizablealarmclock;

import android.app.AlarmManager;

import java.util.ArrayList;

/**
 * Created by Derek on 4/26/2018.
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
    private AlarmManager alarmManager;

    /**
     * Creates an Alarm object
     * @param alarmName the name of the Alarm
     * @param sounds the ArrayList of Sounds to be played in the Alarm
     * @param time the AlarmTime object that contains the time the Alarm should go off at
     * @param timeLeft the time left in milliseconds
     * @param repeat the repeat setting of the Alarm
     * @param isActive if the Alarm has been set
     * @param snoozeActive if snooze is activated
     * @param id the Alarm id
     */
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

    /**
     * Gets the name of the Alarm
     * @return the name of the Alarm
     */
    public String getAlarmName(){
        return alarmName;
    }

    /**
     * Gets the ArrayList of Sound objects to be played in the Alarm
     * @return the ArrayList of Sound objects
     */
    public ArrayList<Sound> getSounds(){
        return sounds;
    }

    /**
     * Gets the AlarmTime object that contains the time the Alarm should go off
     * @return the AlarmTime object
     */
    public AlarmTime getTime(){
        return time;
    }

    /**
     * Gets the time left in milliseconds
     * @return the time left in milliseconds
     */
    public long getTimeLeft(){
        return timeLeft;
    }

    /**
     * Gets the repeat setting of the Alarm
     * @return the repeat setting of the Alarm
     */
    public int getRepeat(){
        return repeat;
    }

    /**
     * Gets the current state of the Alarm
     * @return if the Alarm is set
     */
    public boolean getIsActive(){
        return isActive;
    }

    /**
     * Gets the current state of snooze
     * @return if the snooze is enabled
     */
    public boolean getSnoozeActive(){
        return snoozeActive;
    }

    /**
     * Gets the id of the Alarm
     * @return the id of the Alarm
     */
    public int getId(){
        return id;
    }

    /**
     * Sets the name of the Alarm
     * @param newName the new name of the Alarm
     */
    public void setAlarmName(String newName){
        alarmName = newName;
    }

    /**
     * Sets the repeat setting
     * @param repeat the new repeat setting
     */
    public void setRepeat(int repeat){
        this.repeat = repeat;
    }

    /**
     * Sets the time of the Alarm should go off
     * @param time the new time the Alarm should go off
     */
    public void setTime(AlarmTime time){
        this.time = time;
    }

    /**
     * Sets the time left on the Alarm
     * @param timeLeft the new time left
     */
    public void setTimeLeft(long timeLeft){
        this.timeLeft = timeLeft;
    }

    /**
     * Sets if the Alarm is set
     * @param isActive if the Alarm is set
     */
    public void setActive(boolean isActive){
        this.isActive = isActive;
    }

    /**
     * Sets if the snooze is enabled
     * @param snoozeActive if the snooze is enabled
     */
    public void setSnoozeActive(boolean snoozeActive){
        this.snoozeActive = snoozeActive;
    }

    /**
     * Sets the id of the Alarm
     * @param id the new id of the Alarm
     */
    public void setId(int id){
        this.id = id;
    }
}