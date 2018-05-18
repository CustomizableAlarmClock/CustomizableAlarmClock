package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

//https://www.youtube.com/watch?v=YaIcwyIF2-o
public class AllAlarms extends AppCompatActivity {
    private ArrayList<String> arrayList; //the list of alarm names to be displayed in the ListView
    private Adapter adapter; //displays the list of alarms
    private EditText txtInput; //creates the object that allows the user to edit the alarm name
    Controller c; //Controller to handle the data


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_alarms);

        c = (Controller) getApplicationContext();

        //Displays the current list of alarms
        final ListView listView = findViewById(R.id.SpecificAlarms);
        arrayList = new ArrayList<>();
        for(int i = 0; i<c.getAlarms().size(); i++){
            arrayList.add(c.getAlarms().get(i).getAlarmName());


        }
        adapter = new Adapter(this, arrayList, c);
        Log.d("size",String.valueOf(arrayList.size()));
        listView.setAdapter(adapter);

        //Add alarms to the list
        txtInput = findViewById(R.id.EditTextAddAlarm);
        txtInput.setEnabled(true);
        Button btAdd = findViewById(R.id.ButtonAddItems);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //creates the default time that the alarm is to go off at - one hour after the current time and creates the new Alarm object
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY)+1;
            if(hour==24){
                hour = 0;
            }
            AlarmTime time = new AlarmTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hour, calendar.get(Calendar.MINUTE));
            String newItem = txtInput.getText().toString();
            Alarm a = new Alarm(newItem, new ArrayList<Sound>(), time, 3600000, 0, false, false, c.getAlarms().size());

            c.addAlarm(a);
            arrayList.add(newItem);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Alarm Added", Toast.LENGTH_LONG).show();
            }
        });

        //Goes to AlarmsEdit screen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //If you click on the specific alarm in the list it will take you to alarm edit screen
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(i>=0) {
                Intent intent = new Intent(view.getContext(),AlarmEdit.class);
                c.setCurrentAlarmID(i); //sets the current alarmID so that the correct data is used
                startActivity(intent);

            }
            }
        });
    }

    //writes to txt file when activity is destroyed
    @Override
    protected void onStop() {
        super.onStop();
        c.writeToFile(Controller.getFileName());
        Log.d("destroy","saved");
        Log.d("Alarm Size All Arl",String .valueOf(c.getAlarms().size()));
    }

}