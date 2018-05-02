package com.example.derek.customizablealarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public void loadData(String source){
        // Construct the Scanner and File objects for reading
        try  {
            File inputFile = new File(source);
            Scanner in = new Scanner(inputFile);

            // Read the input file one line (word) at a time
            while(in.hasNextLine())  {
                String word = in.nextLine();

                // This will print out the word stored in the variable word
                System.out.println(word);
            }
            in.close();
        }
        catch (FileNotFoundException fileEx)  {
            fileEx.printStackTrace();
        }
    }

}
