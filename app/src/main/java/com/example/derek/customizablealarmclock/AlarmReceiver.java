package com.example.derek.customizablealarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/*
The AlarmReceiver receives the call for the alarm to go off.
https://www.concretepage.com/android/android-alarm-clock-tutorial-to-schedule-and-cancel-alarmmanager-pendingintent-and-wakefulbroadcastreceiver-example
https://www.techotopia.com/index.php/Android_Broadcast_Intents_and_Broadcast_Receivers
http://codetheory.in/android-broadcast-receivers/
 */

public class AlarmReceiver extends BroadcastReceiver {
    Controller c; //Controller to handle the data
    int alarmID;
    @Override
    public void onReceive(Context context, Intent intent) {

        c = (Controller) context.getApplicationContext();
        alarmID = c.getCurrentAlarmID();
        Toast.makeText(context, "ALARM", Toast.LENGTH_LONG).show(); //notifies that alarm went off

        //does not execute if there are no sounds in the list
        try{
            if(c.getAlarms().get(alarmID).getIsActive()){
            //ArrayList<Sound> sounds = bundle.getParcelableArrayList("Sounds");
            ArrayList<Sound> sounds = c.getAlarms().get(alarmID).getSounds();
            Log.d("AlarmReceiver2",String.valueOf(sounds.size()));

            //moves the ArrayList of file names to the AlarmReceiverScreen java class
            Intent i = new Intent();
            Log.d("AlarmReceiver", String.valueOf(sounds.size()));
            i.setClassName(context.getPackageName(),AlarmReceiverScreen.class.getName());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i); //starts the AlarmReceiverScreen class
            }
        }
        catch(NullPointerException e){
            Log.d("AlarmReceiver", "NullSoundFiles, can't play sounds");
        }
    }
}