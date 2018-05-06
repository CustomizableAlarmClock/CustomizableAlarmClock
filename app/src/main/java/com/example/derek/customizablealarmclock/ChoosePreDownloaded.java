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

public class ChoosePreDownloaded extends AppCompatActivity {
    Controller c; //Controller to handle the data
    int alarmID; //variable to keep track which alarm data to use
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pre_downloaded);

        //defines some variables
        c = (Controller) getApplicationContext();
        alarmID = c.getCurrentAlarmID();

        //creates the Sound objects for the different predownloaded sounds
        Sound pd1 = new Sound("Ring 1 (0003)","sound0003", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd2 = new Sound("Ring 2 (0004)","sound0004", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd3 = new Sound("Ring 3 (0005)","sound0005", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd4 = new Sound("Ring 4 (0006)", "sound0006", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd5 = new Sound("Ring 5 (0007)", "sound0007", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd6 = new Sound("Ring 6 (0008)", "sound0008", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd7 = new Sound("Ring 7 (0009)", "sound0009", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd8 = new Sound("Ring 8 (0011)", "sound0011", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd9 = new Sound("Ring 9 (0012)", "sound0012", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd10 = new Sound("Ring 10 (0016)", "sound0016", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd11 = new Sound("Ring 11 (0020)", "sound0020", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd12 = new Sound("Ring 12 (0029)", "sound0029", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd13 = new Sound("Ring 13 (0134)", "sound0134", c.getAlarms().get(alarmID).getSounds().size());
        Sound pd14 = new Sound("Ring 14 (0253)", "sound0253", c.getAlarms().get(alarmID).getSounds().size());

        //creates the ArrayList of PreDownloaded Sound objects that the user can choose from
        final ArrayList<Sound> preDownloaded = new ArrayList<>();
        preDownloaded.add(pd1);
        preDownloaded.add(pd2);
        preDownloaded.add(pd3);
        preDownloaded.add(pd4);
        preDownloaded.add(pd5);
        preDownloaded.add(pd6);
        preDownloaded.add(pd7);
        preDownloaded.add(pd8);
        preDownloaded.add(pd9);
        preDownloaded.add(pd10);
        preDownloaded.add(pd11);
        preDownloaded.add(pd12);
        preDownloaded.add(pd13);
        preDownloaded.add(pd14);

        //creates the ArrayList of PreDownloaded sound names from the Sound objects
        final ArrayList<String> items = new ArrayList<>();
        for(int i = 0; i<preDownloaded.size(); i++){
            items.add(preDownloaded.get(i).getSoundName());
        }

        //displays the predownloaded sounds in a ListView
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        final ListView listView = findViewById(R.id.ListViewPreDownloaded);
        listView.setAdapter(adapter);

        //selects the sound that the user clicks on and returns to the SoundsEdit page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>=0) {
                    Log.d("Predownload", String.valueOf(preDownloaded.get(i)));
                    c.getAlarms().get(alarmID).getSounds().add(preDownloaded.get(i));
                    Intent intent = new Intent(view.getContext(),SoundsEdit.class);
                    Toast.makeText(getApplicationContext(), "Sound Added", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });
    }
}