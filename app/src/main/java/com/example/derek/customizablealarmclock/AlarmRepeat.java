package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This page allows the user to set when the Alarm repeats.
 */
public class AlarmRepeat extends AppCompatActivity {
    Controller c; //Controller to handle the data
    int alarmID; //variable to keep track which alarm data to use

    /**
     * Creates the AlarmRepeat screen
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_repeat);

        //defines some variables
        c = (Controller) getApplicationContext();
        alarmID = c.getCurrentAlarmID();

        //displays the current repeat choice
        TextView textView = findViewById(R.id.TextViewCurrentRepeating);
        switch (c.getAlarms().get(alarmID).getRepeat()){
            case 1:
                textView.setText("Every Hour");
                break;
            case 2:
                textView.setText("Every Day");
                break;
            case 3:
                textView.setText("Every Week");
                break;
            default:
                textView.setText("None");
                break;
        }

        //diplays the repeat options in a ListView
        final ListView listView = findViewById(R.id.ListViewRepeat);
        ArrayList<String> repeatOptions = new ArrayList<>();
        repeatOptions.add("None");
        repeatOptions.add("Every Hour");
        repeatOptions.add("Every Day");
        repeatOptions.add("Every Week");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, repeatOptions);
        listView.setAdapter(adapter);

        //sets the repeat when an option is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Allows the user to select which Alarm to edit
             * @param adapterView the AdapterView with the repeat options
             * @param view the View
             * @param i the index of a repeat option
             * @param l the id of the option that was clicked
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=0) {
                    Intent intent = new Intent(view.getContext(),AlarmEdit.class);
                    c.getAlarms().get(alarmID).setRepeat(i);
                    Toast.makeText(getApplicationContext(),"Repeat Set",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Writes to a text file when the activity is destroyed
     */
    @Override
    protected void onStop() {
        super.onStop();
        c.writeToFile(c.getFileName());
        Log.d("destroy","saved");
        Log.d("AlarmRepeat", "Alarm Size" + String.valueOf(c.getAlarms().size()));
    }
}