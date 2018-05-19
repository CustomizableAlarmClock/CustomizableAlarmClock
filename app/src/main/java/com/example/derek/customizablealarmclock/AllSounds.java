package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This page displays all the Sounds that is in an Alarm
 */
public class AllSounds extends AppCompatActivity {
    private ArrayList<String> soundNames; //creates a list of sound names (which the user can change) to be displayed in the ListView
    private EditText txtInput; //creates the text input for adding a Sound
    private ArrayAdapter<String> adapter; //creates the ArrayList for the ListView
    int alarmID; //variable to keep track which alarm data to use
    Controller c; //Controller to handle the data

    /**
     * Creates the AllSounds page
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sounds);

        //defines some variables
        c = (Controller) getApplicationContext();
        alarmID = c.getCurrentAlarmID();
        soundNames = new ArrayList<>();

        //creates a ListView with all the sounds to be played in a specific alarm
        final ListView listView = findViewById(R.id.AllSounds);
        try {
            for (int i = 0; i < c.getAlarms().get(alarmID).getSounds().size(); i++) {
                soundNames.add(c.getAlarms().get(alarmID).getSounds().get(i).getSoundName());
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, soundNames);
            listView.setAdapter(adapter);
        }
        catch(IndexOutOfBoundsException e){
            Log.d("AllSounds", "indexoutofbounds");
        }

        Button btAdd = findViewById(R.id.ButtonAddSound);
        btAdd.setOnClickListener(new View.OnClickListener() {
            /**
             * Allows the user to add a new Sound
             * Goes to the NewSoundSource page
             * @param v the View
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listView.getContext(), NewSoundSource.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Allows the user to edit a specific Sound
             * Goes to the SoundsEdit page
             * @param adapterView the AdapterView with the Sound objects
             * @param view the View
             * @param i the index of a Sound object
             * @param l the id of a Sound object
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=0) {
                    Intent intent = new Intent(view.getContext(), SoundsEdit.class);
                    c.setCurrentSoundID(i); //sets the current soundID so that the correct data is used
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Goes back to the AlarmEdit page
     * @param v the View
     */
    public void toAlarmEdit(View v){
        Intent intent = new Intent(this, AlarmEdit.class);
        Toast.makeText(getApplicationContext(), "Sounds Saved", Toast.LENGTH_LONG).show();
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
        Log.d("AllSounds", "Alarm Size" + String.valueOf(c.getAlarms().size()));
    }
}