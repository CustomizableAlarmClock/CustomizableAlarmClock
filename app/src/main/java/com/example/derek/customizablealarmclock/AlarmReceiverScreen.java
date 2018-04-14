package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AlarmReceiverScreen extends AppCompatActivity {
    MediaPlayer mp; //creates the MediaPlayer to play sounds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_receiver_screen);

        //imports and creates a list of file names
        Bundle bundle = getIntent().getExtras();
        final ArrayList<String> sounds;

        final Button button = findViewById(R.id.ButtonStopAlarm); //creates the button that stops the alarm
        final Intent intent = new Intent(this, AlarmEdit.class); //creates the intent to go to the AlarmEdit page

        //only plays sounds if there are sounds in the list
        try{
            sounds = bundle.getStringArrayList("files"); //ArrayList for the list of file names of the sounds to be played in the alarm

            //plays the sounds
            int soundID = AlarmReceiverScreen.this.getResources().getIdentifier(sounds.get(0), "raw",AlarmReceiverScreen.this.getPackageName()); //creates the sound id for the first sound
            mp = MediaPlayer.create(AlarmReceiverScreen.this,soundID); //puts the first sound in the MediaPlayer
            mp.start(); //starts playing the first sound
            int i = 1;
            //checks the size of the file names and plays that many sounds
            while(i < sounds.size()){
                //checks if the stop alarm button has been pressed
                button.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        mp.stop(); //stops the sound if the stop alarm button is pressed
                        startActivity(intent);//goes to the AlarmEdit page
                    }
                });
                //checks if there is already a sound playing (prevents all the sounds from playing at once
                if(!mp.isPlaying()){
                    mp.release(); //releases the resources of the MediaPlayer
                    soundID = AlarmReceiverScreen.this.getResources().getIdentifier(sounds.get(i), "raw",AlarmReceiverScreen.this.getPackageName()); //creates the sound id for the rest of the sounds
                    mp= MediaPlayer.create(AlarmReceiverScreen.this,soundID); //puts a sound in the MediaPlayer
                    mp.start(); //starts playing the sound in the MediaPlayer
                    i++;
                }
            }
        }
        catch(NullPointerException e){
            Log.d("AlarmReceiverScreen", "Null, no sounds in list");
        }
    }
}