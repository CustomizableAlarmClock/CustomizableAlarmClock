package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AlarmRepeat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_repeat);
    }

    //chooses the day to repeat
    public void chooseRepeat(View v){
        //goes back to AlarmEdit
        Intent intent = new Intent(this,AlarmEdit.class);
        startActivity(intent);
    }
}