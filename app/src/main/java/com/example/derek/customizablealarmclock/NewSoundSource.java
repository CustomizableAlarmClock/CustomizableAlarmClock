package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * This page allows the user to choose the source of a new Sound
 */
public class NewSoundSource extends AppCompatActivity {
    /**
     * Creates the NewSoundSource page
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sound_source);

        //diplays the source options in a ListView
        final ListView listView = findViewById(R.id.ListViewSoundSource);
        ArrayList<String> soundSources = new ArrayList<>();
        soundSources.add("Song");
        soundSources.add("Default Sound");
        soundSources.add("Voice Recording");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, soundSources);
        listView.setAdapter(adapter);

        //goes to the source page when an option is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Displays the sources in a ListView
             * @param adapterView the AdapterView that displays the sources
             * @param view the View
             * @param i the index of the source
             * @param l the id of the source
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i){
                    case 0:
                        intent = new Intent(view.getContext(), ChooseSong.class);
                        intent.putExtra("code",0);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), ChooseDefaultSounds.class);
                        intent.putExtra("code",0);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), ChooseRecording.class);
                        intent.putExtra("code",0);
                        startActivity(intent);
                        break;
                    default:
                        intent = new Intent(view.getContext(), AlarmEdit.class);
                        Log.d("NewSoundSource","NoSource");
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}