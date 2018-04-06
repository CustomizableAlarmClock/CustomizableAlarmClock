package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AllAlarms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_alarms);

        Button ButtonAddItems = findViewById(R.id.ButtonAddItems);
        ButtonAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlarmEdit();
            }
        });

    }


    public void openAlarmEdit() {

        Intent intent2  = new Intent(this, AlarmEdit.class);
        startActivity(intent2);
    }


}



















