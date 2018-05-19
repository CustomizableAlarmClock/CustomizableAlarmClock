package com.example.derek.customizablealarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * The AlarmEdit screen where the user can set the time and date of the Alarm,
 * in addition to setting the repeat, snooze, Sounds, and Alarm name. The user
 * can also delete the alarm.
 */
public class AlarmEdit extends AppCompatActivity {
    TimePicker timePicker; //creates TimePicker for user to choose the time
    DatePicker datePicker; //creates DatePicker for a user to choose the date
    int alarmID; //variable to keep track which alarm data to use
    Controller c; //Controller to handle the data
    Calendar calendar; //creates a new Calendar
    AlarmTime time; //creates a new AlarmTime object
    Switch s; //snooze switch
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Context context;

    /**
     * Empty AlarmEdit Constructor
     */
    public AlarmEdit(){
    }

    /**
     * Constructs an AlarmEdit with Context
     * @param context the Context
     */
    public AlarmEdit(Context context){
        this.context = context;
    }

    /**
     * Creates the AlarmEdit page
     * @param savedInstanceState Bundle
     */
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
        final Button setCancel = findViewById(R.id.ButtonSetAlarm);
        Log.d("aactive",String.valueOf(c.getAlarms().get(alarmID).getIsActive()));
        if(c.getAlarms().get(alarmID).getIsActive()){
            setCancel.setText("Cancel Alarm");
        }
        else{
            setCancel.setText("Set Alarm");
        }
        setCancel.setOnClickListener(new View.OnClickListener() {
            /**
             * Enables or disables the Alarm
             * @param v the View
             */
            @Override
            public void onClick(View v) {
                if(c.getAlarms().get(alarmID).getIsActive()){
                    c.getAlarms().get(alarmID).setActive(false);
                    setCancel.setText("Set Alarm");
                    Toast.makeText(getApplicationContext(), "Alarm Cancelled", Toast.LENGTH_LONG).show();
                }
                else {
                    //sets the alarm only if there are sounds in the alarm
                    if (c.getAlarms().get(c.getCurrentAlarmID()).getSounds().size() > 0) {
                        saveAlarmTime(); //gets the time that the alarm goes off at
                        c.getAlarms().get(alarmID).setActive(true);
                        setCancel.setText("Cancel Alarm");

                        //Sets the alarm in the SetAlarm class
                        SetAlarm setAlarm = new SetAlarm(getApplicationContext(), c.getAlarms().get(alarmID).getRepeat(), c.getAlarms().get(alarmID).getTimeLeft(), c.getCurrentAlarmID());
                        setAlarm.setAlarm();
                    } else {
                        Toast.makeText(getApplicationContext(), "No Sounds in Alarm", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        //snooze button
        //https://android--code.blogspot.com/2015/08/android-switch-button-listener.html
        //https://developer.android.com/reference/android/widget/Switch.html
        Switch s = findViewById(R.id.SwitchSnooze);
        s.setChecked(c.getAlarms().get(c.getCurrentAlarmID()).getSnoozeActive()); //displays if snooze is enabled
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Sets the snooze when the Switch is tapped
             * @param buttonView the Button
             * @param isChecked if the Switch is checked
             */
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

    /**
     * Gets the time that the alarm goes off at
     */
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

    /**
     * Goes to the AlarmName page
     * Allows the user to change the alarm name
     * @param v the View
     */
    public void setAlarmName(View v){
        Intent intent = new Intent(this, AlarmName.class);
        saveAlarmTime();
        startActivity(intent);
    }

    /**
     * Goes to the AlarmRepeat page
     * Allows the user to set the repeat
     * @param v the view
     */
    public void repeat(View v){
        Intent intent = new Intent(this, AlarmRepeat.class);
        saveAlarmTime();
        startActivity(intent);
    }

    /**
     * Deletes the Alarm before going back to the AllAlarms page
     * @param v the View
     */
    public void delete(View v){
        Intent intent = new Intent(this, AllAlarms.class);
        c.removeAlarm(alarmID);
        Toast.makeText(getApplicationContext(), "Alarm Deleted", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    /**
     * Goes to the AllSounds page
     * Allows the user to view all Sounds in the Alarm
     * @param v the View
     */
    public void editSounds(View v){
        Intent intent = new Intent(this, AllSounds.class);
        saveAlarmTime();
        startActivity(intent);
    }

    /**
     * Goes back to the AllAlarms page
     * @param v the View
     */
    public void save(View v){
        Intent intent = new Intent(this, AllAlarms.class);
        saveAlarmTime();
        startActivity(intent);
    }

    /**
     * Writes to a text file when the activity is destroyed
     */
    @Override
    protected void onStop() {
        super.onStop();
        c.writeToFile(Controller.getFileName());
        Log.d("destroy","saved");
        Log.d("Alarm Size All Arl",String .valueOf(c.getAlarms().size()));
    }
}