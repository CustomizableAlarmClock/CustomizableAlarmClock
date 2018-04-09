package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AlarmEdit extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_edit);

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
