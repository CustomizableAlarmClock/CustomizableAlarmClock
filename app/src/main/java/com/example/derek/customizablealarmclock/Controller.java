package com.example.derek.customizablealarmclock;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Derek on 4/27/2018.
 */

public class Controller extends Application {
    private ArrayList<Alarm> alarms = new ArrayList<>();
    private int currentAlarmID;
    private int currentSoundID;

    public void addAlarm(Alarm a){
        alarms.add(a);
    }

    public void removeAlarm(Alarm a){

    }

    public void updateAlarm(Alarm a){

    }

    public ArrayList<Alarm> getAlarms(){
        return alarms;
    }

    public int getCurrentAlarmID(){
        return currentAlarmID;
    }

    public void setCurrentAlarmID(int alarmID){
        currentAlarmID = alarmID;
    }

    public int getCurrentSoundID(){
        return currentSoundID;
    }

    public void setCurrentSoundID(int soundID){
        currentSoundID = soundID;
    }
}
