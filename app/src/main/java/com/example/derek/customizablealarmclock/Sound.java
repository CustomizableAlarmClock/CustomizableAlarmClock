package com.example.derek.customizablealarmclock;

/**
 * Created by Derek on 4/13/2018.
 *
 * The Sound object is a sound that will be played in an alarm.
 * It contains the name that the user gave it (soundName) and the file name (fileName).
 */

public class Sound {
    private String soundName;
    private String fileName;

    //creates a Sound object
    Sound(String soundName, String fileName){
        this.soundName = soundName;
        this.fileName = fileName;
    }

    //returns the name of the sound
    public String getSoundName(){
        return soundName;
    }

    //returns the name of the sound file
    public String getFileName(){
        return fileName;
    }

    //changes the name of the sound
    public void changeSoundName(String soundName){
        this.soundName = soundName;
    }

    //changes the file name
    public void changeFileName(String fileName){
        this.fileName = fileName;
    }
}
