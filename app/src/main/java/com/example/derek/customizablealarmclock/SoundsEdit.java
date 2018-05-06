package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SoundsEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int alarmID; //variable to keep track which alarm data to use
    Controller c; //Controller to handle the data
    int soundID; //variable to keep track which sound data to use
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_edit);

        //defines some variables
        c = (Controller) getApplicationContext();
        alarmID = c.getCurrentAlarmID();
        soundID = c.getCurrentSoundID();

        //Drop down menu to choose sound source
        Spinner spinner = findViewById(R.id.spinnerSoundSource);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.sources, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    //goes to AllSounds page
    public void toAllSounds(View v){
        Intent intent = new Intent(this, AllSounds.class);
        startActivity(intent);
    }

    //goes to SoundName page
    public void changeSoundName(View v){
        Intent intent = new Intent(this, SoundName.class);
        startActivity(intent);
    }

    //deletes a sound
    public void delete(View v){
        Intent intent = new Intent(this, AllSounds.class);
        c.getAlarms().get(alarmID).getSounds().remove(soundID);
        Toast.makeText(getApplicationContext(), "Sound Deleted", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    @Override
    //goes to the choose source pages when clicked
    //https://stackoverflow.com/questions/19914511/how-to-start-another-activity-when-click-on-item-from-spinner-items
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent;
        switch (i){
            case 0:
                break;
            case 1:
                intent = new Intent(this, ChooseSong.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, ChoosePreDownloaded.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, ChooseVibration.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, ChooseRecording.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}