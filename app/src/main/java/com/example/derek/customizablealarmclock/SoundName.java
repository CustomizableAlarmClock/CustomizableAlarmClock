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
 * This page allows the user to change the name of a Sound object.
 */
public class SoundName extends AppCompatActivity {
    Controller c; //Controller to handle the data
    int alarmID; //variable to keep track which alarm data to use
    int soundID; //variable to keep track which sound data to use
    EditText editText; //creates the object that allows the user to edit the sound name

    /**
     * Creates the SoundName page
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_name);

        //defines some variables
        c = (Controller) getApplicationContext();
        alarmID = c.getCurrentAlarmID();
        soundID = c.getCurrentSoundID();
        editText = findViewById(R.id.editTextChangeSoundName);

        editText.setText(c.getAlarms().get(alarmID).getSounds().get(soundID).getSoundName(), TextView.BufferType.EDITABLE); //gets the text from the textbox
    }

    /**
     * Sets the new name of a Sound
     * @param v the View
     */
    public void setSoundName(View v){
        Intent intent = new Intent(this, SoundsEdit.class);
        c.getAlarms().get(alarmID).getSounds().get(soundID).setSoundName(editText.getText().toString()); //sets the new sound name
        Toast.makeText(getApplicationContext(), "Sound Name Saved", Toast.LENGTH_LONG).show();
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
    }
}