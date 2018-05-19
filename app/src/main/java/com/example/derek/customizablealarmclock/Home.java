package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * This is the home page.
 */
public class Home extends AppCompatActivity {
    Controller c; //Controller to handle the data

    /**
     * Creates the home page
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //defines some variables
        Button PresstoContinueButton = findViewById(R.id.PressToContinue);
        c = (Controller) getApplicationContext();

        c.getDataFromFile(); //reads data from the text file

        PresstoContinueButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Goes to the AllAlarms page
             * @param view the View
             */
            @Override
            public void onClick(View view) {
                openAllAlarms();
            }
        });
    }

    /**
     * Goes to the AllAlarms page
     */
    public void openAllAlarms() {
        Intent intent = new Intent(this, AllAlarms.class);
        startActivity(intent);
    }
}