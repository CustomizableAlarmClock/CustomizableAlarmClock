package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class AllAlarms extends AppCompatActivity {


  //  private ArrayList<String> items;
  //  private ArrayAdapter<String> itemsAdapter;
  //  private ListView lvItems;

    String items[]=new String[] {"Alarm1","Alarm2","Alarm3","Alarm4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_alarms);


        //If you click on the alarm it will take you to alarm edit screen
        ListView listView = (ListView) findViewById(R.id.SpecificAlarms);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //If you click on the alarm it will take you to alarm edit screen
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i>=0) {
                    Intent myintent = new Intent(view.getContext(),AlarmEdit.class);
                    startActivityForResult(myintent,0);

                }



            }
        });



    }}




















