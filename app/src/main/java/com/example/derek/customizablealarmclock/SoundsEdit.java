package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SoundsEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //ArrayList<Sound> sounds;
    //int requestCode;
    //Sound s;
    int alarmID;
    Controller c;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_edit);

        c = (Controller) getApplicationContext();
        Bundle bundle = getIntent().getExtras();
        alarmID = c.getCurrentAlarmID();
        /*sounds = bundle.getParcelableArrayList("Sounds");
        requestCode = bundle.getInt("requestCode");
        if(requestCode==1 || requestCode==0){
            //Log.d("SoundsEdit", String.valueOf(sounds.size()));
            Log.d("SoundsEdit", String.valueOf(requestCode));
            s = bundle.getParcelable("Sound");
        }*/


        //Drop down menu to choose sound source
        Spinner spinner = findViewById(R.id.spinnerSoundSource);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this, R.array.sources, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /*//executes when the user returns from the ChoosePreDownloaded page; takes the sound that the user chose
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        if(requestCode==1 || requestCode==0){
            Bundle bundle = data.getExtras();
            s = bundle.getParcelable("Sound");
        }
    }*/

    //goes to AllSounds page, passing the sounds that will be played in the alarm
    public void toAllSounds(View v){
        Intent intent = new Intent(this, AllSounds.class);
        startActivity(intent);
        //intent.putExtra("Sound",s);
        //setResult(RESULT_OK,intent); //triggers onActivityResult method in AllSounds
        //finish();
    }

    //goes to page to change sound name
    public void changeSoundName(View v){
        //goes to SoundName
        Intent intent = new Intent(this, SoundName.class);
        //intent.putExtra("Sounds",sounds);
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
                //startActivityForResult(intent,requestCode);
                break;
            case 2:
                intent = new Intent(this, ChoosePreDownloaded.class);
                startActivity(intent);
                //startActivityForResult(intent,requestCode);
                break;
            case 3:
                intent = new Intent(this, ChooseVibration.class);
                startActivity(intent);
                //startActivityForResult(intent,requestCode);
                break;
            case 4:
                intent = new Intent(this, ChooseRecording.class);
                startActivity(intent);
                //startActivityForResult(intent,requestCode);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}