package com.example.derek.customizablealarmclock;

import android.app.AlarmManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Derek on 4/20/2018.
 * https://developer.android.com/reference/android/media/MediaPlayer
 * New thread to play the Sounds in the Alarm
 */
public class PlayMediaPlayer implements Runnable {
    private ArrayList<Sound> sounds; //ArrayList of sounds to be played in the alarm
    private Context context; //Context for the MediaPlayer
    private ArrayList<Integer> soundsID; //ArrayList of soundIDs for the MediaPlayer
    private volatile boolean stop; //Determines if the alarm should be stopped
    private Controller c; //Controller to handle the data
    private int alarmID; //variable to keep track which alarm data to use

    /**
     * Constructor to create a new PlayMediaPlayer object
     * @param context the Context
     * @param soundsID the ArrayList of soundIDs that will be used to play the Sounds
     */
    PlayMediaPlayer(Context context, ArrayList<Integer> soundsID){
        this.context = context;
        this.soundsID = soundsID;
        stop = false;
    }

    /**
     * This method executes when the new thread is created.
     */
    @Override
    public void run() {
        c = (Controller) context;
        alarmID = c.getCurrentAlarmID();
        sounds = c.getAlarms().get(alarmID).getSounds();
        long startTime = System.currentTimeMillis(); //gets the current time in milliseconds
        play(startTime);
    }

    /**
     * Plays the Sounds in the Alarm
     * https://stackoverflow.com/questions/30117443/how-to-play-audio-file-from-phone-internal-storage-in-android
     * @param startTime the time at which the Alarm went off
     */
    private void play(long startTime){
        //only plays sounds if there are sounds in the list
        try {
            MediaPlayer mp;
            if(c.getAlarms().get(alarmID).getSounds().get(0).getId()==0) {
                mp = MediaPlayer.create(context, soundsID.get(0)); //puts the first sound in the MediaPlayer
            }
            else {
                File file = new File(c.getAlarms().get(alarmID).getSounds().get(0).getFileName());
                Uri myUri1 = Uri.fromFile(file);
                mp = MediaPlayer.create(context, myUri1);
                Log.d("asdf","asdf");
            }
            mp.start(); //starts playing the first sound

            //checks the size of the list of sounds and plays that many sounds, also makes sure the MediaPlayer has not been called to stop yet
            //Log.d("PlayMP", String.valueOf(sounds.size()));
            int i = 1;
            while(i<=sounds.size() && !stop) {
                //checks if there is already a sound playing (prevents all the sounds from playing at once)
                if (!mp.isPlaying()) {
                    mp.release(); //releases the resources of the MediaPlayer
                    //plays the sounds in the list
                    if(i<sounds.size()){
                        if(c.getAlarms().get(alarmID).getSounds().get(i).getId()==0){
                            mp = MediaPlayer.create(context, soundsID.get(i)); //puts a sound in the MediaPlayer
                        }
                        else {
                            File file = new File(c.getAlarms().get(alarmID).getSounds().get(i).getFileName());
                            Uri myUri1 = Uri.fromFile(file);
                            mp = MediaPlayer.create(context, myUri1);
                        }
                    }
                    //plays a one-second-long silent track at the end to prevent the thread from ending before the last sound finishes playing
                    else{
                        mp = MediaPlayer.create(context, R.raw.silent);
                        Log.d("Sound","silent");
                    }
                    mp.start(); //starts playing the sound in the MediaPlayer
                    i++;
                }

                Thread.sleep(1000); //prevents the while loop from checking too often
                Log.d("i", String.valueOf(i + " " + sounds.size()));
                Log.d("stop",String.valueOf(stop));
            }

            //if the stop button is pressed, 15 minutes has passed, or the sound size has been reached, the MediaPlayer stops
            if (stop || System.currentTimeMillis() >= startTime + AlarmManager.INTERVAL_FIFTEEN_MINUTES || i == sounds.size()) {
                mp.stop();
                mp.release();
                c.getAlarms().get(alarmID).setActive(false);
                Log.d("stop","stop");
            }
            //if the end of the list has been reached, and 15 minutes has not passed yet, it loops through the sounds again
            else if(System.currentTimeMillis() < startTime+ AlarmManager.INTERVAL_FIFTEEN_MINUTES){
                Log.d("repeat","repeat");
                play(startTime);
            }
        }  catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            Log.d("PlayMediaPlayer", "Null");
        }
    }

    /**
     * Notifies the MediaPlayer that the user pressed the stop button
     */
    public void requestStop(){
        stop = true;
    }
}