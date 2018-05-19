package com.example.derek.customizablealarmclock;

/**
 * Created by Derek on 4/13/2018.
 * The Sound object is a sound that will be played in an alarm.
 */
public class Sound {
    private String soundName;
    private String fileName;
    private int id;

    /**
     * Constructs a Sound object
     * @param soundName the Sound name
     * @param fileName the file name
     * @param id the id of the Sound object
     */
    Sound(String soundName, String fileName, int id){
        this.soundName = soundName;
        this.fileName = fileName;
        this.id = id;
    }

    /**
     * Gets the Sound name of a Sound object
     * @return the Sound name of a Sound object
     */
    public String getSoundName(){
        return soundName;
    }

    /**
     * Gets the file name of a Sound object
     * @return the file name of a Sound object
     */
    public String getFileName(){
        return fileName;
    }

    /**
     * Gets the id of a Sound object
     * @return the id of a Sound object
     */
    public int getId(){
        return id;
    }

    /**
     * Sets the name of a Sound object
     * @param soundName the name of a Sound object
     */
    public void setSoundName(String soundName){
        this.soundName = soundName;
    }

    /**
     * Sets the file name of a Sound object
     * @param fileName the file name of a Sound object
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    /**
     * Sets the id of a Sound object
     * @param id the id of a Sound object
     */
    public void setId(int id){
        this.id = id;
    }
}