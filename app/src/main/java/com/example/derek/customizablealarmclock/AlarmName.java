package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Allows the user to edit the name of the Alarm
 */
public class AlarmName extends AppCompatActivity {
    Controller c; //Controller to handle the data
    EditText editText; //creates the object that allows the user to edit the alarm name
    int alarmID; //variable to keep track which alarm data to use

    /**
     * Creates the AlarmName page
     * @param savedInstanceState Bundle
     */
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

    /**
     * Sets the name of the Alarm
     * Goes back to the AlarmEdit page
     * @param v the View
     */
    public void setAlarmName(View v){
        c.getAlarms().get(alarmID).setAlarmName(editText.getText().toString());
        Intent intent = new Intent(this, AlarmEdit.class);
        Toast.makeText(getApplicationContext(), "Alarm Name Saved", Toast.LENGTH_LONG).show();
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
        Log.d("AlarmName", "Alarm Size" + String.valueOf(c.getAlarms().size()));
    }
}