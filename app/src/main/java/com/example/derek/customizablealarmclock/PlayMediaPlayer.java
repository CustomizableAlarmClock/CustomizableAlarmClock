package com.example.derek.customizablealarmclock;

import android.app.AlarmManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Derek on 4/20/2018.
 * https://developer.android.com/reference/android/media/MediaPlayer
 * New thread to play the sounds in the alarm
 */

public class PlayMediaPlayer implements Runnable {
    private MediaPlayer mp; //creates the MediaPlayer object to play the sounds
    private ArrayList<Sound> sounds; //ArrayList of sounds to be played in the alarm
    private Context context; //Context for the MediaPlayer
    private ArrayList<Integer> soundsID; //ArrayList of soundIDs for the MediaPlayer
    private volatile boolean stop; //Determines if the alarm should be stopped

    //Constructor
    public PlayMediaPlayer(ArrayList<Sound> sounds, Context context, ArrayList<Integer> soundsID){
        this.sounds = sounds;
        this.context = context;
        this.soundsID = soundsID;
        stop = false;
    }

    //executes this method first
    @Override
    public void run() {
        long startTime = System.currentTimeMillis(); //gets the current time in milliseconds
        play(startTime);
    }

    //plays the sounds in the alarm
    private void play(long startTime){
        //only plays sounds if there are sounds in the list
        try {
            mp = MediaPlayer.create(context, soundsID.get(0)); //puts the first sound in the MediaPlayer
            mp.start(); //starts playing the first sound

            //checks the size of the list of sounds and plays that many sounds, also makes sure the MediaPlayer has not been called to stop yet
            Log.d("PlayMP", String.valueOf(sounds.size()));
            int i = 1;
            while(i<=sounds.size() && !stop) {
                //checks if there is already a sound playing (prevents all the sounds from playing at once
                if (!mp.isPlaying()) {
                    mp.release(); //releases the resources of the MediaPlayer
                    //plays the sounds in the list
                    if(i<sounds.size()){
                        mp = MediaPlayer.create(context, soundsID.get(i)); //puts a sound in the MediaPlayer
                    }
                    //plays a one-second-long silent track at the end to prevent the thread from ending before the last sound finishes playing
                    else{
                        mp = MediaPlayer.create(context, R.raw.silent);
                    }
                    mp.start(); //starts playing the sound in the MediaPlayer
                    i++;
                }

                Thread.sleep(1000); //prevents the while loop from checking too often
                Log.d("i", String.valueOf(i + " " + sounds.size()));
            }

            //if the stop button is pressed, 15 minutes has passed, or the sound size has been reached, the MediaPlayer stops
            if (stop || System.currentTimeMillis() >= startTime + AlarmManager.INTERVAL_FIFTEEN_MINUTES || i == sounds.size()) {
                mp.stop();
                mp.release();
            }
            //if the end of the list has been reached, and 15 minutes has not passed yet, it loops through the sounds again
            else if(System.currentTimeMillis() < startTime+ AlarmManager.INTERVAL_FIFTEEN_MINUTES){
                Log.d("repeat","repeat");
                play(startTime);
            }
        }
        catch (NullPointerException e) {
            Log.d("AlarmReceiverScreen", "Null, no sounds in list");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //notifies the MediaPlayer that the user pressed the stop button
    public void requestStop(){
        stop = true;
    }
}