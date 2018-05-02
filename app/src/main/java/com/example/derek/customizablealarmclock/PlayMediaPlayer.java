package com.example.derek.customizablealarmclock;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Derek on 4/20/2018.
 */

public class PlayMediaPlayer implements Runnable {
    private MediaPlayer mp;
    private ArrayList<Sound> sounds;
    private Context context;
    private ArrayList<Integer> soundsID;
    private volatile boolean stop;

    public PlayMediaPlayer(ArrayList<Sound> sounds, Context context, ArrayList<Integer> soundsID){
        this.sounds = sounds;
        this.context = context;
        this.soundsID = soundsID;
        stop = false;
    }
    @Override
    public void run() {
        /*//imports and creates a list of file names
        final Bundle bundle = getIntent().getExtras();
        //final Button button = findViewById(R.id.ButtonStopAlarm); //creates the button that stops the alarm
        final Intent intent = new Intent(this, AlarmEdit.class); //creates the intent to go to the AlarmEdit page*/

        //only plays sounds if there are sounds in the list
        try {
            //sounds = bundle.getParcelableArrayList("Sounds"); //ArrayList for the list of file names of the sounds to be played in the alarm

            //plays the sounds
            //int soundID = AlarmReceiverScreen.getResources().getIdentifier(sounds.get(0).getFileName(), "raw",PlayMediaPlayer.this.getPackageName()); //creates the sound id for the first sound
            mp = MediaPlayer.create(context, soundsID.get(0)); //puts the first sound in the MediaPlayer
            mp.start(); //starts playing the first sound*/
            //checks the size of the file names and plays that many sounds
            Log.d("PlayMP", String.valueOf(sounds.size()));
            int i = 1;
            while(i<sounds.size()&&!stop) {

                //checks if there is already a sound playing (prevents all the sounds from playing at once
                if (!mp.isPlaying()) {
                    mp.release(); //releases the resources of the MediaPlayer
                    //soundID = PlayMediaPlayer.this.getResources().getIdentifier(sounds.get(i).getFileName(), "raw",PlayMediaPlayer.this.getPackageName()); //creates the sound id for the rest of the sounds
                    mp = MediaPlayer.create(context, soundsID.get(i)); //puts a sound in the MediaPlayer
                    mp.start(); //starts playing the sound in the MediaPlayer
                    i++;
                }

                Thread.sleep(1000);
                Log.d("asdf", "fdsa");
                Thread.sleep(1000);
                Log.d("sdfsdf", "fdsfds");
                Thread.sleep(1000);
                Log.d("i", String.valueOf(i + " " + sounds.size()));

            }
        } catch (NullPointerException e) {
            Log.d("AlarmReceiverScreen", "Null, no sounds in list");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(stop) {
            mp.stop();
            mp.release();
        }

    }

    public void requestStop(){
        stop = true;
    }
}
