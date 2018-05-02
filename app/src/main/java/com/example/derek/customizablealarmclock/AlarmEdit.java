package com.example.derek.customizablealarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmEdit extends AppCompatActivity {
    TimePicker timePicker; //creates TimePicker for user to choose
    //ArrayList<String> fileNames; //creates ArrayList to store the sounds to be played in the alarm
    //AllSounds s = new AllSounds(); //creates AllSound object to pull the sounds to be played
    //int requestCode;
    Alarm alarm;
    int alarmID;
    //ArrayList<Sound> sounds;
    Controller c;
    Calendar calendar = Calendar.getInstance();
    AlarmTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_edit);

        c = (Controller) getApplicationContext();
        Bundle bundle = getIntent().getExtras();
        alarmID = c.getCurrentAlarmID();

        /*requestCode = bundle.getInt("requestCode");
        alarm = c.getAlarms().get(alarmID);
        Log.d("asdfcode", String.valueOf(requestCode));
        if(requestCode==1){
            sounds = new ArrayList<>();
            sounds = bundle.getParcelableArrayList("Sounds");
        }
        else{
            s.makeSounds(); //prepares the sound files to be imported into this Java class from the AllSounds page
        }

        //makes sure there are actually sounds in the alarm
        try {
            if(requestCode==1){
                fileNames = new ArrayList<>();
                for(int i = 0; i<sounds.size(); i++){
                    fileNames.add(sounds.get(i).getFileName());
                }
            }
            else {
                fileNames = s.getFileNames(); //gets the sound files to be played
            }

        }
        catch (NullPointerException e){
            Log.d("AlarmEdit","fileNames Null");
        }*/

        timePicker = findViewById(R.id.timePicker);
        if(Build.VERSION.SDK_INT >= 23){
            timePicker.setHour(c.getAlarms().get(alarmID).getTime().getHour());
            timePicker.setMinute(c.getAlarms().get(alarmID).getTime().getMinute());
        }
        else{
            timePicker.setCurrentHour(c.getAlarms().get(alarmID).getTime().getHour());
            timePicker.setCurrentMinute(c.getAlarms().get(alarmID).getTime().getMinute());
        }

        findViewById(R.id.ButtonSetAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //gets the current date/time and gets the time the user set when the button Set Alarm is clicked


            if (android.os.Build.VERSION.SDK_INT >= 23) {
                calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getHour(),
                    timePicker.getMinute(),
                    0
                );
                time = new AlarmTime(calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        timePicker.getHour(),
                        timePicker.getMinute());
            } else {
                calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getCurrentHour(),
                    timePicker.getCurrentMinute(),
                    0
                );
                time = new AlarmTime(calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute());
            }

            c.getAlarms().get(alarmID).setTime(time);
            c.getAlarms().get(alarmID).setTimeLeft(calendar.getTimeInMillis());
            setAlarm(calendar.getTimeInMillis()); //sets the alarm
            }
        });
    }

    //sets the alarm
    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //goes to receiver when alarm goes off
        Intent intent = new Intent(this, AlarmReceiver.class); //creates intent to go to the AlarmReceiver

        /*intent.putExtra("Sounds",sounds); //moves sounds to be played to the AlarmReceiver
        intent.putExtra("requestCode",requestCode);
        Log.d("files", String.valueOf(fileNames.size()));
        Log.d("AlarmEdit2",String.valueOf(sounds.size()));
        Log.d("AlarmEdit2",String.valueOf(requestCode));*/
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        //Log.d("AlarmEdit3",String.valueOf(sounds.size()));
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        //Log.d("AlarmEdit4",String.valueOf(sounds.size()));

        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show(); //shows message that alarm is set
        //Log.d("AlarmEdit5",String.valueOf(sounds.size()));
    }

    //allows user to change the alarm name
    public void setAlarmName(View v){
        Intent intent = new Intent(this,AlarmName.class);
        startActivity(intent);
    }

    //allows user to choose when to repeat alarm
    public void repeat(View v){
        //goes to AlarmRepeat page
        Intent intent = new Intent(this,AlarmRepeat.class);
        //intent.putExtra("requestCode",requestCode);
        startActivity(intent);
    }

    //allows the user to view all sounds in the alarm
    public void editSounds(View v){
        //goes to AllSounds page
        Intent intent = new Intent(this,AllSounds.class);
        /*intent.putExtra("requestCode",requestCode);
        Log.d("AlarmEdit", String.valueOf(requestCode));

        //if songs have been added to the alarm
        if(requestCode==1) {
            intent.putExtra("Sounds", sounds);
        }*/
        startActivity(intent);
    }

    public void save(View v){
        Intent intent = new Intent(this,AllAlarms.class);
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getHour(),
                    timePicker.getMinute(),
                    0
            );
            time = new AlarmTime(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getHour(),
                    timePicker.getMinute());
        } else {
            calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getCurrentHour(),
                    timePicker.getCurrentMinute(),
                    0
            );
            time = new AlarmTime(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getCurrentHour(),
                    timePicker.getCurrentMinute());
        }

        c.getAlarms().get(alarmID).setTime(time);
        c.getAlarms().get(alarmID).setTimeLeft(calendar.getTimeInMillis());
        startActivity(intent);
    }
}