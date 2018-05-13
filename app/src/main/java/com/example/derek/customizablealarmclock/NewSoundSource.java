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

public class NewSoundSource extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sound_source);

        //diplays the source options in a ListView
        final ListView listView = findViewById(R.id.ListViewSoundSource);
        ArrayList<String> soundSources = new ArrayList<>();
        soundSources.add("Song");
        soundSources.add("Pre-downloaded Sound");
        soundSources.add("Voice Recording");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, soundSources);
        listView.setAdapter(adapter);

        //goes to the source page when an option is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i){
                    case 0:
                        intent = new Intent(view.getContext(), ChooseSong.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), ChoosePreDownloaded.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), ChooseRecording.class);
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
