package com.example.derek.customizablealarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * This class sets an Alarm.
 */
public class SetAlarm {
    private AlarmManager alarmManager;
    private Context context;
    private int repeat;
    private long timeInMillis;
    private PendingIntent pendingIntent;

    /**
     * Constructs a SetAlarm object
     * @param context the Context
     * @param repeat the repeat setting
     * @param timeInMillis the time until the Alarm goes off in milliseconds
     * @param alarmID the id of the Alarm
     */
    SetAlarm(Context context, int repeat, long timeInMillis, int alarmID){
        this.context = context;
        this.repeat = repeat;
        this.timeInMillis = timeInMillis;
        alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, alarmID, intent, 0);
    }

    /**
     * Sets the Alarm
     */
    public void setAlarm(){
        if (alarmManager != null) {
            //switch deals with repeats: case 1: repeats every hour; case 2: repeats every day; case 3; repeats every week; default: no repeat
            switch (repeat){
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
        Toast.makeText(context, "Alarm is set", Toast.LENGTH_SHORT).show(); //shows message that alarm is set
    }
}