package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SoundsEdit extends AppCompatActivity {
    MediaPlayer ring; //creates MediaPlayer object
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_edit);
        ring= MediaPlayer.create(SoundsEdit.this,R.raw.ring10); //has the MediaPlayer play alarm
    }

    public void selectSource(View v){
        ring.start(); //Rings when click button
    }

    //goes to page to change sound name
    public void changeSoundName(View v){
        //goes to SoundName
        Intent intent  = new Intent(this, SoundName.class);
        startActivity(intent);
    }


}
