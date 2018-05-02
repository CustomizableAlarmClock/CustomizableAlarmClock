package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class AllAlarms extends AppCompatActivity {

    //hard coded list of alarms
    String items[]=new String[] {"Alarm1","Alarm2","Alarm3","Alarm4"};

    //test
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText txtInput;
    Controller c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_alarms);

        c = (Controller) getApplicationContext();
        //Displays the alarms
        final ListView listView = findViewById(R.id.SpecificAlarms);
        //final String items[] = new String[] {"Alarm1","Alarm2","Alarm3","Alarm4"};
        //arrayList = new ArrayList<>(Arrays.asList(items));
        arrayList = new ArrayList<>();
        for(int i = 0; i<c.getAlarms().size(); i++){
            arrayList.add(c.getAlarms().get(i).getAlarmName());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        //Add items with edit text
        txtInput = findViewById(R.id.EditTextAddAlarm);
        Button btAdd = findViewById(R.id.ButtonAddItems);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                //Log.d("AllAlarms",newItem);
                //Log.d("AllAlarms",arrayList.toString());
            }
        });

        //If you click on the specific alarm in the list it will take you to alarm edit screen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //If you click on the specific alarm in the list it will take you to alarm edit screen
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i>=0) {
                    Intent intent = new Intent(view.getContext(),AlarmEdit.class);
                    c.setCurrentAlarmID(i);
                    intent.putExtra("requestCode",0);
                    startActivityForResult(intent,0);
                }
            }
        });
    }
}