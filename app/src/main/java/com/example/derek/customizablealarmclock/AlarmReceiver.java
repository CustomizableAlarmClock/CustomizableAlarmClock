package com.example.derek.customizablealarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class AlarmReceiver extends BroadcastReceiver {
    Controller c;
    @Override
    public void onReceive(Context context, Intent intentn) {

        c = ((Controller) context.getApplicationContext()); // not working
        if(c==null){
            Log.d("cNull","c is null");
        }
        Toast.makeText(context, "ALARM", Toast.LENGTH_LONG).show(); //notifies that alarm went off

        //creates the ArrayList of file names of the sounds to be played
        Bundle bundle = intentn.getExtras();

        //does not execute if there are no sounds in the list
        try{
            //ArrayList<Sound> sounds = bundle.getParcelableArrayList("Sounds");
            ArrayList<Sound> sounds = c.getAlarms().get(c.getCurrentAlarmID()).getSounds();
            Log.d("AlarmReceiver2",String.valueOf(sounds.size()));
            //int requestCode = bundle.getInt("requestCode");

            //moves the ArrayList of file names to the AlarmReceiverScreen java class
            Intent i = new Intent();
            //Log.d("AlarmReceiver", String.valueOf(requestCode));
            Log.d("AlarmReceiver", String.valueOf(sounds.size()));
            i.putExtra("Sounds", sounds);
            //i.putExtra("requestCode",requestCode);
            i.setClassName(context.getPackageName(),AlarmReceiverScreen.class.getName());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i); //starts the AlarmReceiver Screen class
        }
        catch(NullPointerException e){
            Log.d("AlarmReceiver", "NullSoundFiles, can't play sounds");
        }
    }

}