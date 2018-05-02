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

import java.util.ArrayList;

public class AllSounds extends AppCompatActivity {

    //private ArrayList<Sound> sounds; //creates a list of Sound objects
    private ArrayList<String> soundNames; //creates a list of sound names (which the user can change) to be displayed in the ListView
    //private ArrayList<String> fileNames; //creates a list of file names that point to where the sound files are located
    private EditText txtInput; //creates the text input for adding a Sound
    private ArrayAdapter<String> adapter;
    //int requestCode;
    int alarmID;
    Controller c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sounds);

        c = (Controller) getApplicationContext();
        Bundle bundle = getIntent().getExtras();
        //requestCode = bundle.getInt("requestCode");
        alarmID = c.getCurrentAlarmID();

        /*Log.d("AllSoundsRC", String.valueOf(requestCode));
        //if the user has edited the list of sounds, then it gets that list. Otherwise, if uses the hard-coded list of sounds
        if(requestCode==1){
            sounds = bundle.getParcelableArrayList("Sounds");
            Log.d("AllSounds", String.valueOf(sounds.size()));
        }
        else {
            makeSounds(); //creates the Sound objects to be used in the alarm
        }
        */
        //creates a list of the names of the sounds in the alarms
        soundNames = new ArrayList<>();
        /*for(int i = 0; i<sounds.size(); i++){
            soundNames.add(sounds.get(i).getSoundName());
        }*/
        final ListView listView = findViewById(R.id.AllSounds);
        try {

            for (int i = 0; i < c.getAlarms().size(); i++) {
                Log.d("ControllerSize", String.valueOf(c.getAlarms().size()));
                Log.d("i", String.valueOf(c.getAlarms().get(i)));
                soundNames.add(c.getAlarms().get(i).getSounds().get(i).getSoundName());
            }

            //creates a ListView to display the sounds
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
                Intent intent = new Intent(listView.getContext(),SoundsEdit.class);
                //intent.putExtra("requestCode",1);
                //intent.putExtra("Sounds",sounds);
                startActivityForResult(intent,1);
                //String newItem=txtInput.getText().toString();
                //soundNames.add(newItem);
                //adapter.notifyDataSetChanged();
            }
        });

        //Goes to the specific SoundsEdit page when a sound is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=0) {
                    Intent intent = new Intent(view.getContext(),SoundsEdit.class);
                    c.setCurrentSoundID(i);
                    //intent.putExtra("Sounds",sounds);
                    //intent.putExtra("requestCode",0);
                    startActivityForResult(intent,0);
                }
            }
        });
    }

    /*//creates the Sound objects and a list of the file names
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
        //sounds.add(s2);
        sounds.add(s3);
        sounds.add(s4);
        sounds.add(s5);

        //creates the list of file names for the sounds to be played
        fileNames = new ArrayList<>();
        for(int i = 0; i<sounds.size(); i++){
            fileNames.add(sounds.get(i).getFileName());
        }
    }

    /*private void soundDuration(File fileName){
        AudioInputStream stream = null;

        try{
            stream = AudioSystem.getAudioInputSream(file);

            AudioFormat format = stream.getFormat();
            return file.length() / format.getsampleRate() / (format.getSampleSizeInBits() / 8.0) / format.getChannels();

        }
        catch(Exception e){
            //log an error
            return -1;
        }
        finally{
            try{
                stream.close();
            }
            catch(Exception e){

            }
        }
    }*/

    /*//method that executes when coming from SoundsEdit; takes the sounds to be played in the alarm
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 || requestCode==0){
            Bundle bundle = data.getExtras();
            Sound s = bundle.getParcelable("Sound");
            //adds new sounds that the user has chosen to the list of sounds to play
            try{
                soundNames.add(s.getSoundName());
                adapter.notifyDataSetChanged();
                sounds.add(s);
            }
            catch(NullPointerException e){
                Log.d("AllSounds", "Null sound from SoundsEdit");
            }

            Log.d("AllSounds2", String.valueOf(requestCode));
            Log.d("AllSounds2", String.valueOf(sounds.size()));
        }
    }*/

    //returns the file names of the sounds to be played
    /*public ArrayList<String> getFileNames(){
        return fileNames;
    }*/

    //goes to the Alarm Edit page, passing the sounds that will play
    public void toAlarmEdit(View v){
        Intent intent = new Intent(this, AlarmEdit.class);
        //Log.d("asdf", String.valueOf(sounds.size()));
        //intent.putExtra("Sounds",sounds);
        //intent.putExtra("requestCode",1);
        startActivity(intent);
    }
}