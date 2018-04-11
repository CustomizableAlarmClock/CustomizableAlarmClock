package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseRecording extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_recording);
    }

    public void createRecording(View v){
        //goes to MakeRecording
        Intent intent  = new Intent(this, MakeRecording.class);
        startActivity(intent);
    }
}
