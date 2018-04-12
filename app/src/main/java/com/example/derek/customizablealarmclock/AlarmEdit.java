package com.example.derek.customizablealarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmEdit extends AppCompatActivity {
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_edit);

        timePicker = findViewById(R.id.timePicker);
        findViewById(R.id.ButtonSetAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //gets the current date/time and gets the time the user set
            Calendar calendar = Calendar.getInstance();
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getHour(),
                    timePicker.getMinute(),
                    0
                );
            } else {
                calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getCurrentHour(),
                    timePicker.getCurrentMinute(),
                    0
                );
            }

            setAlarm(calendar.getTimeInMillis()); //sets the alarm
            }
        });
    }

    //sets the alarm
    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //goes to receiver when alarm goes off
        Intent intent = new Intent(this, AlarmReceiver.class); //
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show(); //shows message that alarm is set
    }

    //allows user to choose when to repeat alarm
    public void repeat(View v){
        //goes to AlarmRepeat page
        Intent intent = new Intent(this,AlarmRepeat.class);
        startActivity(intent);
    }

    public void editSounds(View v){
        //goes to SoundsEdit page
        Intent intent = new Intent(this,SoundsEdit.class);
        startActivity(intent);
    }
}
