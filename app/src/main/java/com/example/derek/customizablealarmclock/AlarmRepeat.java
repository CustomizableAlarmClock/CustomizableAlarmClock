package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AlarmRepeat extends AppCompatActivity {

    Controller c;
    int alarmID;
    //int requestCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_repeat);

        c = (Controller) getApplicationContext();
        Bundle bundle = getIntent().getExtras();
        //requestCode = bundle.getInt("requestCode");
        alarmID = c.getCurrentAlarmID();
    }

    //chooses the day to repeat
    public void chooseRepeat(View v){
        //goes back to AlarmEdit


        Intent intent = new Intent(this, AlarmEdit.class);
        //intent.putExtra("requestCode",requestCode);
        startActivity(intent);
    }
}