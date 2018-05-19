package com.example.derek.customizablealarmclock;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// code used from https://www.youtube.com/watch?v=kf2fxYLOiSo
public class ChooseSong extends AppCompatActivity {
    Controller c;
    Bundle bundle;
    int code;
    int alarmID;
    int soundID;
    private static final int MY_PERMISSION_REQUEST = 1; //permission request result
    ArrayList<String> arrayList; //arraylist to store title, author, and location
    ArrayList<Sound> sounds;
    ListView listView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_song);

        c = (Controller) getApplicationContext();
        alarmID = c.getCurrentAlarmID();
        soundID = c.getCurrentSoundID();
        sounds = new ArrayList<>();

        bundle = getIntent().getExtras();
        if (bundle != null) {
            code = bundle.getInt("code");
        }
        //checks the permission of the device
        if (ContextCompat.checkSelfPermission(ChooseSong.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //if the device shows a permission request, read the external storage
            if (ActivityCompat.shouldShowRequestPermissionRationale(ChooseSong.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(ChooseSong.this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
            else {
                //reads the external storage
                ActivityCompat.requestPermissions(ChooseSong.this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        }
        else {
            createAdapter();
        }
    }

    //calls getMusic method, displays songs in listView, and creates listView object
    public void createAdapter() {
        listView = findViewById(R.id.ListViewChooseSong);
        arrayList = new ArrayList<>();
        getMusic();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //asdf(i);


                Log.d("ChooseSong",String.valueOf(i));
                if(code==0){
                    c.getAlarms().get(alarmID).getSounds().add(sounds.get(i));
                }
                else{
                    c.getAlarms().get(alarmID).getSounds().set(soundID, sounds.get(i));
                }
                Toast.makeText(getApplicationContext(), "Sound Added", Toast.LENGTH_LONG).show();
                Intent intent= new Intent(view.getContext(), AllSounds.class);
                startActivity(intent);
            }
        });
    }

    public void asdf(int i){
        MediaPlayer mp = new MediaPlayer();
        String fileName = sounds.get(i).getFileName();
        File file = new File(fileName);
        Log.d("filename",String.valueOf(fileName));
        Uri myUri1 = Uri.fromFile(file);

        try {
            mp.setDataSource(this, myUri1);
            mp.prepare();
            Log.d("asdf","asdf");
            mp.start();
            if(mp.isPlaying()){
                Log.d("Yay","Its playing");
            }
            else{
                Log.d("a","ffffffffffff");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //gets the music and puts song information in the arrayList
    public void getMusic() {
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);

        if (songCursor != null && songCursor.moveToFirst()) {
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songLocation = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
                //puts title, artist, and location in the arrayList
                String currentTitle = songCursor.getString(songTitle);
                String currentArtist = songCursor.getString(songArtist);
                if(currentArtist.equals("<unknown>")){
                    currentArtist = "No Artist Info";
                }
                Log.d("currentArtist",currentArtist);
                String currentLocation = songCursor.getString(songLocation);
                if(currentLocation.contains("Music")) {
                    arrayList.add(currentTitle + "\n" + "Artist: " + currentArtist);
                    Sound s = new Sound(currentTitle, currentLocation, 1);
                    sounds.add(s);
                }
            } while (songCursor.moveToNext());
        }
    }

    //gets the result of permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(ChooseSong.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
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
    }
}