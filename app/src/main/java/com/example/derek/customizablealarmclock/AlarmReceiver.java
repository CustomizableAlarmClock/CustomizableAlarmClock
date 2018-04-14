package com.example.derek.customizablealarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ALARM", Toast.LENGTH_LONG).show(); //notifies that alarm went off

        //creates the ArrayList of file names of the sounds to be played
        Bundle bundle = intent.getExtras();

        //does not execute if there are no sounds in the list
        try{
            ArrayList<String> sounds = bundle.getStringArrayList("files");

            //moves the ArrayList of file names to the AlarmReceiverScreen java class
            Intent i = new Intent();
            i.putExtra("files", sounds);
            i.setClassName(context.getPackageName(),AlarmReceiverScreen.class.getName());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i); //starts the AlarmReceiver Screen class
        }
        catch(NullPointerException e){
            Log.d("AlarmReceiver", "NullSoundFiles, can't play sounds");
        }
    }

}