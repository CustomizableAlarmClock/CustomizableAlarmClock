package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AllAlarms extends AppCompatActivity {

    //hard coded list of alarms
    String items[]=new String[] {"Alarm1","Alarm2","Alarm3","Alarm4"};

    //test
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText txtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_alarms);


        //If you click on the specific alarm in the list it will take you to alarm edit screen
        final ListView listView = (ListView) findViewById(R.id.SpecificAlarms);
        final String items[]=new String[] {"Alarm1","Alarm2","Alarm3","Alarm4"};
        arrayList=new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        //Add items with edit text
        txtInput=(EditText)findViewById(R.id.EditTextAddAlarm);
        Button btAdd = (Button)findViewById(R.id.ButtonAddItems);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem=txtInput.getText().toString();
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
                    Intent myintent = new Intent(view.getContext(),AlarmEdit.class);
                    startActivityForResult(myintent,0);

                }



            }
        });



    }}




















