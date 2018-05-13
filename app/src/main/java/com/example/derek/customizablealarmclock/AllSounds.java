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

public class AllSounds extends AppCompatActivity {
    private ArrayList<String> soundNames; //creates a list of sound names (which the user can change) to be displayed in the ListView
    private EditText txtInput; //creates the text input for adding a Sound
    private ArrayAdapter<String> adapter; //creates the ArrayList for the ListView
    int alarmID; //variable to keep track which alarm data to use
    Controller c; //Controller to handle the data

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
            Log.d("AllSounds","asdfdsasdfdsa");
            Log.d("AllSoundsSize",String.valueOf(c.getAlarms().get(alarmID).getSounds().size()));
            Log.d("AllSoundsSize",String.valueOf(alarmID));
            for (int i = 0; i < c.getAlarms().get(alarmID).getSounds().size(); i++) {
                Log.d("AllSounds",String.valueOf(c.getAlarms().get(alarmID).getSounds().size()));
                soundNames.add(c.getAlarms().get(alarmID).getSounds().get(i).getSoundName());
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, soundNames);
            listView.setAdapter(adapter);
        }
        catch(IndexOutOfBoundsException e){
            Log.d("AllSounds", "indexoutofbounds");
        }

        //Add a Sound
        Button btAdd = findViewById(R.id.ButtonAddSound);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            //Goes to the SoundsEdit page
            public void onClick(View v) {
            Intent intent = new Intent(listView.getContext(), NewSoundSource.class);
            startActivity(intent);
            }
        });

        //Goes to the specific SoundsEdit page when a sound is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    //goes to the Alarm Edit page
    public void toAlarmEdit(View v){
        Intent intent = new Intent(this, AlarmEdit.class);
        Toast.makeText(getApplicationContext(), "Sounds Saved", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}