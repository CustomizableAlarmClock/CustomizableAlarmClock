package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AllSounds extends AppCompatActivity {

    private ArrayList<Sound> sounds; //creates a list of Sound objects
    private ArrayList<String> soundNames; //creates a list of sound names (which the user can change) to be displayed in the ListView
    private ArrayList<String> fileNames; //creates a list of file names that point to where the sound files are located
    private EditText txtInput; //creates the text input for adding a Sound

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sounds);

        makeSounds(); //creates the Sound objects to be used in the alarm

        //creates a list of the names of the sounds in the alarms
        soundNames = new ArrayList<>();
        for(int i = 0; i<sounds.size(); i++){
            soundNames.add(sounds.get(i).getSoundName());
        }

        //creates a ListView to display the sounds
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, soundNames);
        final ListView listView = findViewById(R.id.AllSounds);
        listView.setAdapter(adapter);

        //Add a Sound
        txtInput = findViewById(R.id.EditTextAddSound);
        Button btAdd = findViewById(R.id.ButtonAddSound);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            //Adds the sound when the button is clicked
            public void onClick(View v) {
                String newItem=txtInput.getText().toString();
                soundNames.add(newItem);
                adapter.notifyDataSetChanged();
            }
        });

        //Goes to the specific SoundsEdit page when a sound is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=0) {
                    Intent myintent = new Intent(view.getContext(),SoundsEdit.class);
                    startActivityForResult(myintent,0);
                }
            }
        });
    }

    //creates the Sound objects and a list of the file names
    public void makeSounds(){
        //creates Sound objects
        Sound s1 = new Sound("First Sound", "sound0007");
        Sound s2 = new Sound("Second Sound", "sound0009");
        Sound s3 = new Sound("Third Sound", "sound0020");
        Sound s4 = new Sound("Fourth Sound", "sound0029");
        Sound s5 = new Sound("Fifth Sound", "sound0253");

        //creates the list of Sounds
        sounds = new ArrayList<>();
        sounds.add(s1);
        sounds.add(s2);
        sounds.add(s3);
        sounds.add(s4);
        sounds.add(s5);
        sounds.add(s1);

        //creates the list of file names for the sounds to be played
        fileNames = new ArrayList<>();
        for(int i = 0; i<sounds.size(); i++){
            fileNames.add(sounds.get(i).getFileName());
        }
    }

    //returns the file names of the sounds to be played
    public ArrayList<String> getFileNames(){
        return fileNames;
    }

    //goes to the Alarm Edit page
    public void toAlarmEdit(View v){
        Intent intent = new Intent(this, AlarmEdit.class);
        startActivity(intent);
    }
}