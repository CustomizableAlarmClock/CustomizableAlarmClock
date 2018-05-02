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
    ArrayList<Sound> sounds;
    ArrayList<Integer>soundIDList = new ArrayList<>();
    Controller c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_receiver_screen);

        c = (Controller) getApplicationContext();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //imports and creates a list of file names
        final Bundle bundle = getIntent().getExtras();
        final Button button = findViewById(R.id.ButtonStopAlarm); //creates the button that stops the alarm
        final Intent intent = new Intent(this, AlarmEdit.class); //creates the intent to go to the AlarmEdit page

        sounds = c.getAlarms().get(c.getCurrentAlarmID()).getSounds(); //ArrayList for the list of file names of the sounds to be played in the alarm
        for(int j = 0; j<sounds.size();j++){
            Log.d("SoundsSize", String.valueOf(sounds.size()+" "+j));
            int soundID = AlarmReceiverScreen.this.getResources().getIdentifier(sounds.get(j).getFileName(), "raw",AlarmReceiverScreen.this.getPackageName()); //creates the sound id for the rest of the sounds

            soundIDList.add(soundID);
        }
        final PlayMediaPlayer playMediaPlayer = new PlayMediaPlayer(sounds, getApplicationContext(),soundIDList);
        Thread thread = new Thread(playMediaPlayer);
        thread.start();

        //only plays sounds if there are sounds in the list
        /*try{
            sounds = bundle.getParcelableArrayList("Sounds"); //ArrayList for the list of file names of the sounds to be played in the alarm

            //plays the sounds
            int soundID = AlarmReceiverScreen.this.getResources().getIdentifier(sounds.get(0).getFileName(), "raw",AlarmReceiverScreen.this.getPackageName()); //creates the sound id for the first sound
            mp = MediaPlayer.create(AlarmReceiverScreen.this,soundID); //puts the first sound in the MediaPlayer
            mp.start(); //starts playing the first sound
            int i = 0;

            //checks the size of the file names and plays that many sounds
            while(i < sounds.size()){
                //checks if there is already a sound playing (prevents all the sounds from playing at once
                if(!mp.isPlaying()){
                    mp.release(); //releases the resources of the MediaPlayer
                    soundID = AlarmReceiverScreen.this.getResources().getIdentifier(sounds.get(i).getFileName(), "raw",AlarmReceiverScreen.this.getPackageName()); //creates the sound id for the rest of the sounds
                    mp= MediaPlayer.create(AlarmReceiverScreen.this,soundID); //puts a sound in the MediaPlayer
                    mp.start(); //starts playing the sound in the MediaPlayer
                    i++;
                }
                Thread.sleep(1000);
                Log.d("asdf","fdsa");
                Thread.sleep(1000);
                Log.d("sdfsdf","fdsfds");
                Thread.sleep(1000);
                Log.d("i", String.valueOf(i));
                //i++;
            }
        }
        catch(NullPointerException e){
            Log.d("AlarmReceiverScreen", "Null, no sounds in list");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //checks if the stop alarm button has been pressed
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                playMediaPlayer.requestStop();
                //int requestCode = bundle.getInt("requestCode");
                //Log.d("AlarmReceiverScreen", String.valueOf(requestCode));
                Log.d("AlarmReceiverScreen", String.valueOf(sounds.size()));
                //intent.putExtra("requestCode",requestCode);
                //intent.putExtra("Sounds",sounds);
                startActivity(intent);//goes to the AlarmEdit page
            }
        });
    }
}