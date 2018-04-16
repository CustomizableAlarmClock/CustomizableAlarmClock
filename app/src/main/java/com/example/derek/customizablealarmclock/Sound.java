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
