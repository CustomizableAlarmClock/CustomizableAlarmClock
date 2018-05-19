package com.example.derek.customizablealarmclock;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This page allows the user to choose a recording to put in the Alarm.
 */
public class ChooseRecording extends AppCompatActivity {
    Controller c; //Controller to handle the data
    Bundle bundle;
    int code; //determines if the user wants to add or change a Sound
    int alarmID; //variable to keep track which Alarm data to use
    int soundID; //variable to keep track which Sound data to use
    private static final int MY_PERMISSION_REQUEST = 1; //permission request result
    ArrayList<String> arrayList; //ArrayList to store title, author, and location of the recordings
    ArrayList<Sound> sounds; //ArrayList of Sound objects
    ListView listView;
    ArrayAdapter<String> adapter;

    /**
     * Creates the ChooseRecordings page
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_recording);

        c = (Controller) getApplicationContext();
        alarmID = c.getCurrentAlarmID();
        soundID = c.getCurrentSoundID();
        sounds = new ArrayList<>();

        bundle = getIntent().getExtras();
        if (bundle != null) {
            code = bundle.getInt("code");
        }
        //checks the permission of the device
        if (ContextCompat.checkSelfPermission(ChooseRecording.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //if the device shows a permission request, read the external storage
            if (ActivityCompat.shouldShowRequestPermissionRationale(ChooseRecording.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(ChooseRecording.this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
            else {
                //reads the external storage
                ActivityCompat.requestPermissions(ChooseRecording.this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        }
        else {
            createAdapter();
        }
    }

    //calls getMusic method, displays songs in listView, and creates listView object
    public void createAdapter() {
        listView = findViewById(R.id.ListViewChooseRecording);
        arrayList = new ArrayList<>();
        getRecording();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Either adds or changes the Sound in the ArrayList of Sounds
             * @param adapterView the AdapterView that displays the recordings
             * @param view the View
             * @param i the index of a recording
             * @param l the id of a recording
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(code==0){
                    c.getAlarms().get(alarmID).getSounds().add(sounds.get(i)); //adds a new Sound to the ArrayList
                }
                else{
                    c.getAlarms().get(alarmID).getSounds().set(soundID, sounds.get(i)); //changes an existing Sound
                }
                Toast.makeText(getApplicationContext(), "Sound Added", Toast.LENGTH_LONG).show();
                Intent intent= new Intent(view.getContext(), AllSounds.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Gets the voice recording information in the ArrayList
     */
    public void getRecording() {
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);

        if (songCursor != null && songCursor.moveToFirst()) {
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songLocation = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
                //puts title, artist, and location in the ArrayList
                String currentTitle = songCursor.getString(songTitle);
                String currentArtist = songCursor.getString(songArtist);
                if(currentArtist.equals("<unknown>")){
                    currentArtist = "No Artist Info";
                }
                String currentLocation = songCursor.getString(songLocation);
                if(currentLocation.contains("Recorder")) {
                    arrayList.add(currentTitle + "\n" + "Artist: " + currentArtist);
                    Sound s = new Sound(currentTitle, currentLocation, 1);
                    sounds.add(s);
                }
            } while (songCursor.moveToNext());
        }
        if (songCursor != null) {
            songCursor.close();
        }
    }

    /**
     * Gets the result of the permission request
     * @param requestCode the request code
     * @param permissions the permissions sent
     * @param grantResults the results of the permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(ChooseRecording.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                        createAdapter();
                    }
                }
                else {
                    Toast.makeText(this, "No permission granted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    /**
     * Writes to a text file when the activity is destroyed
     */
    @Override
    protected void onStop() {
        super.onStop();
        c.writeToFile(Controller.getFileName());
        Log.d("destroy","saved");
        Log.d("ChooseRecording", "Alarm Size" + String.valueOf(c.getAlarms().size()));
    }
}