package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmName extends AppCompatActivity {
    Controller c; //Controller to handle the data
    EditText editText; //creates the object that allows the user to edit the alarm name
    int alarmID; //variable to keep track which alarm data to use
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_name);

        //defines some variables
        c = (Controller) getApplicationContext();
        alarmID = c.getCurrentAlarmID();
        editText = findViewById(R.id.EditTextChangeAlarmName);

        editText.setText(c.getAlarms().get(alarmID).getAlarmName(), TextView.BufferType.EDITABLE); //puts the text from the textbox into the editText variable
    }

    //changes the alarm name
    public void setAlarmName(View v){
        c.getAlarms().get(alarmID).setAlarmName(editText.getText().toString());
        Intent intent = new Intent(this, AlarmEdit.class);
        Toast.makeText(getApplicationContext(), "Alarm Name Saved", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    //writes to txt file when activity is destroyed
    @Override
    protected void onStop() {
        super.onStop();
        c.writeToFile(Controller.getFileName());
        Log.d("destroy","saved");
    }
}
