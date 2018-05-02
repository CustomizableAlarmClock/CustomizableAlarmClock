package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AlarmName extends AppCompatActivity {

    Controller c;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_name);

        c = (Controller) getApplicationContext();

        editText = findViewById(R.id.EditTextChangeAlarmName);
        editText.setText(c.getAlarms().get(c.getCurrentAlarmID()).getAlarmName(), TextView.BufferType.EDITABLE);
    }

    public void setAlarmName(View v){
        c.getAlarms().get(c.getCurrentAlarmID()).setAlarmName(editText.getText().toString());
        Intent intent = new Intent(this, AlarmEdit.class);
        startActivity(intent);
    }
}
