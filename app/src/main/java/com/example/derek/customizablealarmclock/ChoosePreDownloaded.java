package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ChoosePreDownloaded extends AppCompatActivity {

    int requestCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pre_downloaded);

        //creates the Sound objects for the different sounds
        Sound pd1 = new Sound("Ring 1 (0003)","sound0003");
        Sound pd2 = new Sound("Ring 2 (0004)","sound0004");
        Sound pd3 = new Sound("Ring 3 (0005)","sound0005");
        Sound pd4 = new Sound("Ring 4 (0006)", "sound0006");
        Sound pd5 = new Sound("Ring 5 (0007)", "sound0007");
        Sound pd6 = new Sound("Ring 6 (0008)", "sound0008");
        Sound pd7 = new Sound("Ring 7 (0009)", "sound0009");
        Sound pd8 = new Sound("Ring 8 (0011)", "sound0011");
        Sound pd9 = new Sound("Ring 9 (0012)", "sound0012");
        Sound pd10 = new Sound("Ring 10 (0016)", "sound0016");
        Sound pd11 = new Sound("Ring 11 (0020)", "sound0020");
        Sound pd12 = new Sound("Ring 12 (0029)", "sound0029");
        Sound pd13 = new Sound("Ring 13 (0134)", "sound0134");
        Sound pd14 = new Sound("Ring 14 (0253)", "sound0253");

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
                    Intent intent = new Intent(view.getContext(),SoundsEdit.class);
                    intent.putExtra("Sound",preDownloaded.get(i));
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}
