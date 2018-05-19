package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This page allows the user to choose a default sound to put in the Alarm
 */
public class ChooseDefaultSounds extends AppCompatActivity {
    Controller c; //Controller to handle the data
    int alarmID; //variable to keep track which Alarm data to use
    int soundID; //variable to keep track which Sound data to use
    Bundle bundle;
    int code;

    /**
     * Creates the ChooseDefaultSounds page
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_default_sounds);

        //defines some variables
        c = (Controller) getApplicationContext();
        alarmID = c.getCurrentAlarmID();
        soundID = c.getCurrentSoundID();

        bundle = getIntent().getExtras();
        if (bundle != null) {
            code = bundle.getInt("code");
        }

        //creates the Sound objects for the different default sounds
        /*Sound pd1 = new Sound("Ring 1 (0003)","sound0003", 0);
        Sound pd2 = new Sound("Ring 2 (0004)","sound0004", 0);
        Sound pd3 = new Sound("Ring 3 (0005)","sound0005", 0);
        Sound pd4 = new Sound("Ring 4 (0006)", "sound0006", 0);
        Sound pd5 = new Sound("Ring 5 (0007)", "sound0007", 0);
        Sound pd6 = new Sound("Ring 6 (0008)", "sound0008", 0);
        Sound pd7 = new Sound("Ring 7 (0009)", "sound0009", 0);
        Sound pd8 = new Sound("Ring 8 (0011)", "sound0011", 0);
        Sound pd9 = new Sound("Ring 9 (0012)", "sound0012", 0);
        Sound pd10 = new Sound("Ring 10 (0016)", "sound0016", 0);
        Sound pd11 = new Sound("Ring 11 (0020)", "sound0020", 0);
        Sound pd12 = new Sound("Ring 12 (0029)", "sound0029", 0);
        Sound pd13 = new Sound("Ring 13 (0134)", "sound0134", 0);
        Sound pd14 = new Sound("Ring 14 (0253)", "sound0253", 0);*/
        Sound pd15 = new Sound("Air Horn", "airhorn", 0);
        Sound pd16 = new Sound("Alarm", "warningalarm", 0);
        Sound pd17 = new Sound("Beeping", "beeping", 0);
        Sound pd18 = new Sound("Bell", "ringingbell", 0);
        Sound pd19 = new Sound("Birds", "earlymorningbird", 0);
        Sound pd20 = new Sound("Burglar Alarm", "burglaralarm", 0);
        Sound pd21 = new Sound("Cat Meowing", "catmeowing", 0);
        Sound pd22 = new Sound("Censor Beeping", "censorbeep", 0);
        Sound pd23 = new Sound("Chimes", "magicalchimesound", 0);
        Sound pd24 = new Sound("Connection", "connectionestablishedsoundeffect", 0);
        Sound pd25 = new Sound("Countdown", "countdowntimer", 0);
        Sound pd26 = new Sound("Cow", "moosound", 0);
        Sound pd27 = new Sound("Cricket", "cricketchirping", 0);
        Sound pd28 = new Sound("Dog Barking", "dogbarking", 0);
        Sound pd29 = new Sound("Door Bell", "doorbelldingdong", 0);
        Sound pd30 = new Sound("Elephant", "elephant", 0);
        Sound pd31 = new Sound("Fire Truck", "firetruck", 0);
        Sound pd32 = new Sound("Gong", "moviegongsoundeffect", 0);
        Sound pd33 = new Sound("Heart Monitor", "heartmonitor", 0);
        Sound pd34 = new Sound("Hotel Bell", "hotelreceptionbell", 0);
        Sound pd35 = new Sound("Ice Bell", "icybellgliss", 0);
        Sound pd36 = new Sound("Keyboard", "typingkeyboardsound", 0);
        Sound pd37 = new Sound("Pager", "pagersoundeffect", 0);
        Sound pd38 = new Sound("Ping", "pingsound", 0);
        Sound pd39 = new Sound("Rain", "rainsound", 0);
        Sound pd40 = new Sound("Rooster", "roosternoise", 0);
        Sound pd41 = new Sound("Sleigh Bells", "sleighbells", 0);
        Sound pd42 = new Sound("Store Bell", "shopkeepersbell", 0);
        Sound pd43 = new Sound("Ticket Machine", "ticketmachine", 0);
        Sound pd44 = new Sound("Whistle", "whistle", 0);

        //creates the ArrayList of default Sound objects that the user can choose from
        final ArrayList<Sound> defaultSounds = new ArrayList<>();
        /*defaultSounds.add(pd1);
        defaultSounds.add(pd2);
        defaultSounds.add(pd3);
        defaultSounds.add(pd4);
        defaultSounds.add(pd5);
        defaultSounds.add(pd6);
        defaultSounds.add(pd7);
        defaultSounds.add(pd8);
        defaultSounds.add(pd9);
        defaultSounds.add(pd10);
        defaultSounds.add(pd11);
        defaultSounds.add(pd12);
        defaultSounds.add(pd13);
        defaultSounds.add(pd14);*/
        defaultSounds.add(pd15);
        defaultSounds.add(pd16);
        defaultSounds.add(pd17);
        defaultSounds.add(pd18);
        defaultSounds.add(pd19);
        defaultSounds.add(pd20);
        defaultSounds.add(pd21);
        defaultSounds.add(pd22);
        defaultSounds.add(pd23);
        defaultSounds.add(pd24);
        defaultSounds.add(pd25);
        defaultSounds.add(pd26);
        defaultSounds.add(pd27);
        defaultSounds.add(pd28);
        defaultSounds.add(pd29);
        defaultSounds.add(pd30);
        defaultSounds.add(pd31);
        defaultSounds.add(pd32);
        defaultSounds.add(pd33);
        defaultSounds.add(pd34);
        defaultSounds.add(pd35);
        defaultSounds.add(pd36);
        defaultSounds.add(pd37);
        defaultSounds.add(pd38);
        defaultSounds.add(pd39);
        defaultSounds.add(pd40);
        defaultSounds.add(pd41);
        defaultSounds.add(pd42);
        defaultSounds.add(pd43);
        defaultSounds.add(pd44);

        //creates the ArrayList of default sound names from the Sound objects
        final ArrayList<String> items = new ArrayList<>();
        for(int i = 0; i<defaultSounds.size(); i++){
            items.add(defaultSounds.get(i).getSoundName());
        }

        //displays the default sounds in a ListView
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        final ListView listView = findViewById(R.id.ListViewPreDownloaded);
        listView.setAdapter(adapter);

        //selects the sound that the user clicks on and returns to the SoundsEdit page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Adds the chosen Sound to the ArrayList of Sounds
             * @param adapterView the AdapterView of the default sounds
             * @param view the View
             * @param i the index of a default sound
             * @param l the id of the default sound
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=0) {
                    Log.d("Default", String.valueOf(defaultSounds.get(i)));
                    if(code==0){
                        c.getAlarms().get(alarmID).getSounds().add(defaultSounds.get(i));
                    }
                    else{
                        c.getAlarms().get(alarmID).getSounds().set(soundID, defaultSounds.get(i));
                    }
                    Intent intent = new Intent(view.getContext(), AllSounds.class);
                    Toast.makeText(getApplicationContext(), "Sound Added", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });
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