package com.example.derek.customizablealarmclock;

/**
 * Created by Derek on 4/13/2018.
 * The Sound object is a sound that will be played in an alarm.
 */

public class Sound {
    private String soundName;
    private String fileName;
    private int id;

    //creates a Sound object
    Sound(String soundName, String fileName, int id){
        this.soundName = soundName;
        this.fileName = fileName;
        this.id = id;
    }

    //returns the name of the sound
    public String getSoundName(){
        return soundName;
    }

    //returns the name of the sound file
    public String getFileName(){
        return fileName;
    }

    //returns the id number of the sound object
    public int getId(){
        return id;
    }

    //changes the name of the sound
    public void setSoundName(String soundName){
        this.soundName = soundName;
    }

    //changes the file name
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    //changes the id of the sound object
    public void setId(int id){
        this.id = id;
    }
}