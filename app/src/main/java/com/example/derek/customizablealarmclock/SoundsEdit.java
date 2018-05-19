package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * This page allows a user to edit a Sound
 */
public class SoundsEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int alarmID; //variable to keep track which alarm data to use
    Controller c; //Controller to handle the data
    int soundID; //variable to keep track which sound data to use

    /**
     * Creates the SoundsEdit page
     * @param savedInstanceState Bundle
     */
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

    /**
     * Goes back to the AllSounds page
     * @param v the View
     */
    public void toAllSounds(View v){
        Intent intent = new Intent(this, AllSounds.class);
        startActivity(intent);
    }

    /**
     * Allows the user to change the name of the Sound object
     * Goes to the SoundName page
     * @param v the View
     */
    public void changeSoundName(View v){
        Intent intent = new Intent(this, SoundName.class);
        startActivity(intent);
    }

    /**
     * Allows the user to delete the Sound object before going back to the AllSounds page
     * @param v the View
     */
    public void delete(View v){
        Intent intent = new Intent(this, AllSounds.class);
        c.getAlarms().get(alarmID).getSounds().remove(soundID);
        Toast.makeText(getApplicationContext(), "Sound Deleted", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    /**
     * Goes to a choose sound source page when the user selects a source from the Spinner
     * https://stackoverflow.com/questions/19914511/how-to-start-another-activity-when-click-on-item-from-spinner-items
     * @param adapterView the AdapterView of the sources
     * @param view the View
     * @param i the index of a source
     * @param l the id of a source
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent;
        switch (i){
            case 0:
                break;
            case 1:
                intent = new Intent(this, ChooseSong.class);
                intent.putExtra("code",1);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, ChooseDefaultSounds.class);
                intent.putExtra("code",1);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, ChooseRecording.class);
                intent.putExtra("code",1);
                startActivity(intent);
                break;
        }
    }

    /**
     * When nothing is selected in the spinner, nothing happens
     * @param adapterView the AdapterView of the sources
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * Writes to a text file when the activity is destroyed
     */
    @Override
    protected void onStop() {
        super.onStop();
        c.writeToFile(Controller.getFileName());
        Log.d("destroy","saved");
        Log.d("SoundsEdit", "Alarm Size" + String.valueOf(c.getAlarms().size()));
    }
}