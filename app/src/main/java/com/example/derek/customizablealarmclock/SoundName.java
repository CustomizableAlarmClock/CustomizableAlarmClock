package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SoundName extends AppCompatActivity {

    Controller c;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_name);

        c = (Controller) getApplicationContext();

        editText = findViewById(R.id.editTextChangeSoundName);
        editText.setText(c.getAlarms().get(c.getCurrentAlarmID()).getSounds().get(c.getCurrentSoundID()).getSoundName(), TextView.BufferType.EDITABLE);

    }

    public void setSoundName(View v){
        Intent intent = new Intent(this, SoundsEdit.class);
        c.getAlarms().get(c.getCurrentAlarmID()).getSounds().get(c.getCurrentSoundID()).setSoundName(editText.getText().toString());
        startActivity(intent);
    }
}
