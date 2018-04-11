package com.example.derek.customizablealarmclock;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.media.MediaPlayer;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {
    MediaPlayer ring; //creates MediaPlayer object
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "ALARM", Toast.LENGTH_LONG).show(); //notifies that alarm went off

        ring= MediaPlayer.create(context,R.raw.ring10); //has the MediaPlayer play alarm
        ring.start();
    }

}