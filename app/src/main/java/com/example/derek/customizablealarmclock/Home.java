package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button PresstoContinueButton = findViewById(R.id.PressToContinue);
        PresstoContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllAlarms();
            }
        });
    }

        public void openAllAlarms() {
            Intent intent  = new Intent(this, AllAlarms.class);
            startActivity(intent);
    }

}
