package com.example.derek.customizablealarmclock;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Derek on 4/13/2018.
 *
 * The Sound object is a sound that will be played in an alarm.
 * It contains the name that the user gave it (soundName) and the file name (fileName).
 */

public class Sound implements Parcelable{
    private String soundName;
    private String fileName;
    private int id;
    //private double duration;

    //creates a Sound object
    Sound(String soundName, String fileName, int id){
        this.soundName = soundName;
        this.fileName = fileName;
        this.id = id;
        //this.duration = duration;
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

    /*//returns the duration of the Sound
    public double getDuration(){
        return duration;
    }*/

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

    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(soundName);
        dest.writeString(fileName);
    }

    //constructor used for parcel
    public Sound(Parcel parcel){
        soundName = parcel.readString();
        fileName = parcel.readString();
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<Sound> CREATOR = new Parcelable.Creator<Sound>(){

        @Override
        public Sound createFromParcel(Parcel parcel) {
            return new Sound(parcel);
        }

        @Override
        public Sound[] newArray(int size) {
            return new Sound[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}
