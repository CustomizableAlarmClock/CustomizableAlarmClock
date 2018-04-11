package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SoundsEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_edit);

        //Drop down menu to choose sound source
        Spinner spinner = findViewById(R.id.spinnerSoundSource);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.sources, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    //goes to page to change sound name
    public void changeSoundName(View v){
        //goes to SoundName
        Intent intent = new Intent(this, SoundName.class);
        startActivity(intent);
    }

    @Override
    //goes to the choose source pages when clicked
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent;
        String text = adapterView.getItemAtPosition(i).toString();
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