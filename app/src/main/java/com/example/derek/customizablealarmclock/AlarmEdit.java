package com.example.derek.customizablealarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmEdit extends AppCompatActivity {
    TimePicker timePicker; //creates TimePicker for user to choose the time
    DatePicker datePicker; //creates DatePicker for a user to choose the date
    int alarmID; //variable to keep track which alarm data to use
    Controller c; //Controller to handle the data
    Calendar calendar; //creates a new Calendar
    AlarmTime time; //creates a new AlarmTime object
    Switch s; //snooze switch

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_edit);

        //defines some variables
        c = (Controller) getApplicationContext();
        alarmID = c.getCurrentAlarmID();
        calendar = Calendar.getInstance();

        //displays the alarm date and time that it is supposed to go of at, if exists. If not, sets the default date and time
        //https://developer.android.com/reference/android/widget/TimePicker
        //https://developer.android.com/reference/android/widget/DatePicker
        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.DatePicker);
        if(Build.VERSION.SDK_INT >= 23){
            datePicker.updateDate(c.getAlarms().get(alarmID).getTime().getYear(), c.getAlarms().get(alarmID).getTime().getMonth(), c.getAlarms().get(alarmID).getTime().getDay());
            timePicker.setHour(c.getAlarms().get(alarmID).getTime().getHour());
            timePicker.setMinute(c.getAlarms().get(alarmID).getTime().getMinute());
        }
        else{
            datePicker.updateDate(c.getAlarms().get(alarmID).getTime().getYear(), c.getAlarms().get(alarmID).getTime().getMonth(), c.getAlarms().get(alarmID).getTime().getDay());
            timePicker.setCurrentHour(c.getAlarms().get(alarmID).getTime().getHour());
            timePicker.setCurrentMinute(c.getAlarms().get(alarmID).getTime().getMinute());
        }

        //sets the alarm
        //https://www.concretepage.com/android/android-alarm-clock-tutorial-to-schedule-and-cancel-alarmmanager-pendingintent-and-wakefulbroadcastreceiver-example
        findViewById(R.id.ButtonSetAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sets the alarm only if there are sounds in the alarm
                if(c.getAlarms().get(c.getCurrentAlarmID()).getSounds().size()>0) {
                    saveAlarmTime(); //gets the time that the alarm goes off at
                    setAlarm(calendar.getTimeInMillis()); //sets the alarm
                }
                else{
                    Toast.makeText(getApplicationContext(), "No Sounds in Alarm", Toast.LENGTH_LONG).show();
                }
            }
        });

        //snooze button
        //https://android--code.blogspot.com/2015/08/android-switch-button-listener.html
        //https://developer.android.com/reference/android/widget/Switch.html
        Switch s = findViewById(R.id.SwitchSnooze);
        s.setChecked(c.getAlarms().get(c.getCurrentAlarmID()).getSnoozeActive()); //displays if snooze is enabled
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //sets if snooze is enabled
                if(isChecked){
                    c.getAlarms().get(alarmID).setSnoozeActive(true);
                }
                else{
                    c.getAlarms().get(alarmID).setSnoozeActive(false);
                }
            }
        });
    }

    //gets the time that the alarm goes off at
    public void saveAlarmTime(){
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            //puts into a calendar object the time the alarm goes off at
            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getHour(), timePicker.getMinute(), 0);
            //creates a new AlarmTime object with the time that the alarm goes off at
            time = new AlarmTime(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getHour(), timePicker.getMinute());
        }
        else {
            //puts into a calendar object the time the alarm goes off at
            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
            //creates a new AlarmTime object with the time that the alarm goes off at
            time = new AlarmTime(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
        }

        c.getAlarms().get(alarmID).setTime(time); //puts the time that the alarm goes off into the controller
        c.getAlarms().get(alarmID).setTimeLeft(calendar.getTimeInMillis()); //puts the time in milliseconds that the alarm goes off into the controller
    }

    //sets the alarm
    //https://developer.android.com/reference/android/app/AlarmManager.html
    //https://developer.android.com/training/scheduling/alarms
    public void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //goes to receiver when alarm goes off
        Intent intent = new Intent(this, AlarmReceiver.class); //creates intent to go to the AlarmReceiver
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (alarmManager != null) {
            //switch deals with repeats: case 1: repeats every hour; case 2: repeats every day; case 3; repeats every week; default: no repeat
            switch (c.getAlarms().get(alarmID).getRepeat()){
                case 1:
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_HOUR, pendingIntent);
                    break;
                case 2:
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
                    break;
                case 3:
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY*7, pendingIntent);
                    break;
                default:
                    alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
                    break;
            }
        }

        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show(); //shows message that alarm is set
    }

    //allows user to change the alarm name
    public void setAlarmName(View v){
        Intent intent = new Intent(this, AlarmName.class);
        saveAlarmTime();
        startActivity(intent);
    }

    //allows user to choose when to repeat alarm
    public void repeat(View v){
        //goes to AlarmRepeat page
        Intent intent = new Intent(this, AlarmRepeat.class);
        saveAlarmTime();
        startActivity(intent);
    }

    //deletes an alarm and goes back to AllAlarms
    public void delete(View v){
        Intent intent = new Intent(this, AllAlarms.class);
        c.getAlarms().remove(alarmID);
        Toast.makeText(getApplicationContext(), "Alarm Deleted", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    //allows the user to view all sounds in the alarm
    public void editSounds(View v){
        //goes to AllSounds page
        Intent intent = new Intent(this,AllSounds.class);
        saveAlarmTime();
        startActivity(intent);
    }

    //goes back to the AllAlarms page
    public void save(View v){
        Intent intent = new Intent(this,AllAlarms.class);
        saveAlarmTime();
        startActivity(intent);
    }
}