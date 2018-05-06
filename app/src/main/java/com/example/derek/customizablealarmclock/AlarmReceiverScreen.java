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
    ArrayList<Sound> sounds; //creates an ArrayList of sounds to be played in the alarm
    ArrayList<Integer> soundIDList; //creates an ArrayList of soundIDs
    Controller c; //Controller to handle the data
    PlayMediaPlayer playMediaPlayer; //creates the MediaPlayer object
    int alarmID; //variable to keep track which alarm data to use
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_receiver_screen);

        //defines some variables
        c = (Controller) getApplicationContext();
        soundIDList = new ArrayList<>();
        playMediaPlayer = new PlayMediaPlayer(sounds, getApplicationContext(), soundIDList); //sends alarm data to the alarm player thread
        alarmID = c.getCurrentAlarmID();

        //if snooze is not enable, it hides the snooze button
        //https://stackoverflow.com/questions/4127725/how-can-i-remove-a-button-or-make-it-invisible-in-android
        View view = findViewById(R.id.ButtonSnooze);
        if(!c.getAlarms().get(alarmID).getSnoozeActive()){
            view.setVisibility(View.INVISIBLE);
        }

        final Button button = findViewById(R.id.ButtonStopAlarm); //creates the button that stops the alarm
        final Intent intent = new Intent(this, AlarmEdit.class); //creates the intent to go to the AlarmEdit page when the stop button is hit

        //https://stackoverflow.com/questions/16541881/java-android-how-to-give-media-player-a-string-value-to-a-resource
        sounds = c.getAlarms().get(alarmID).getSounds(); //ArrayList for the list of sounds to be played in the alarm

        //loads the soundIDs into the soundID ArrayList
        for(int j = 0; j<sounds.size();j++){
            Log.d("SoundsSize", String.valueOf(sounds.size()+" "+j));
            int soundID = AlarmReceiverScreen.this.getResources().getIdentifier(sounds.get(j).getFileName(), "raw", AlarmReceiverScreen.this.getPackageName()); //creates the sound id for the sounds
            soundIDList.add(soundID);
        }

        //creates a new thread to handle the playing of the sounds
        Thread thread = new Thread(playMediaPlayer);
        thread.start();

        //checks if the stop alarm button has been pressed
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                playMediaPlayer.requestStop();
                Log.d("AlarmReceiverScreen", String.valueOf(sounds.size()));
                startActivity(intent);//goes to the AlarmEdit page
            }
        });
    }

    //stops the alarm and sets the alarm to go off in 10 minutes
    public void snooze(View v){
        playMediaPlayer.requestStop(); //stops current alarm
        AlarmEdit alarmEdit = new AlarmEdit(); //creates new AlarmEdit object
        alarmEdit.setAlarm(600000); //calls the set alarm method to play another alarm in 10 minutes
        Intent intent = new Intent(this, AlarmEdit.class);
        startActivity(intent);
    }
}