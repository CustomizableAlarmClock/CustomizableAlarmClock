package com.example.derek.customizablealarmclock;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//https://www.youtube.com/watch?v=YaIcwyIF2-o

public class Adapter extends ArrayAdapter<String> {
    Controller c;

    public Adapter(Context context, ArrayList<String> records, Controller c){
        super(context, 0,  records);
        this.c = c;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent){
        //Get the data item for this position
        String item = getItem(position);
        //Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_custom, parent, false);
        }

        final TextView alarmName = convertView.findViewById(R.id.TextViewListItem);
        final Switch aSwitch = convertView.findViewById(R.id.SwitchAlarmActive);
        final TextView alarmTime = convertView.findViewById(R.id.TextViewAlarmTime);
        final TextView alarmRepeat = convertView.findViewById(R.id.TextViewDisplayRepeat);

        int month = c.getAlarms().get(position).getTime().getMonth();
        int day = c.getAlarms().get(position).getTime().getDay();
        int year = c.getAlarms().get(position).getTime().getYear();
        int hour;
        String ampm;
        if(c.getAlarms().get(position).getTime().getHour()==0){
            hour = 12;
            ampm = "AM";
        }
        else if(c.getAlarms().get(position).getTime().getHour()>12){
            hour = c.getAlarms().get(position).getTime().getHour()-12;
            ampm = "PM";
        }
        else{
            hour = c.getAlarms().get(position).getTime().getHour();
            ampm = "AM";
        }
        int minute = c.getAlarms().get(position).getTime().getMinute();

        alarmName.setText(c.getAlarms().get(position).getAlarmName());
        if(c.getAlarms().get(position).getTime().getMinute()<10){
            alarmTime.setText(month+1 + "/" + day + "/" + year + " at " + hour + ":0" + minute + " " + ampm);
        }
        else{
            alarmTime.setText(month+1 + "/" + day + "/" + year + " at " + hour + ":" + minute + " " + ampm);
        }

        Log.d("switchChecked",String.valueOf(aSwitch.isChecked()));
        Log.d("switchche",String.valueOf(aSwitch.isActivated()));
        Log.d("sdfssdf",String.valueOf(c.getAlarms().get(position).getIsActive()));
        if(c.getAlarms().get(position).getIsActive()){
            aSwitch.setText("On");
        }
        else {
            aSwitch.setText("Off");
        }
        aSwitch.setChecked(c.getAlarms().get(position).getIsActive());
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.getAlarms().get(position).setActive(aSwitch.isChecked());
                if(aSwitch.isChecked() && c.getAlarms().get(position).getSounds().size()>0){
                    aSwitch.setText("On");
                    SetAlarm setAlarm = new SetAlarm(getContext(), c.getAlarms().get(position).getRepeat(), c.getAlarms().get(position).getTimeLeft());
                    setAlarm.setAlarm();
                }
                else if(aSwitch.isChecked() && c.getAlarms().get(position).getSounds().size()==0){
                    Toast.makeText(view.getContext(), "No Sounds in Alarm", Toast.LENGTH_LONG).show();
                    aSwitch.setChecked(false);
                }
                else {
                    aSwitch.setText("Off");
                    c.getAlarms().get(position).setActive(false);
                    Toast.makeText(view.getContext(), "Alarm Cancelled", Toast.LENGTH_LONG).show();
                }
            }
        });

        alarmName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), AlarmEdit.class);
                c.setCurrentAlarmID(position); //sets the current alarmID so that the correct data is used
                v.getContext().startActivity(intent);
            }
        });

        alarmTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AlarmEdit.class);
                c.setCurrentAlarmID(position); //sets the current alarmID so that the correct data is used
                view.getContext().startActivity(intent);
            }
        });

        alarmRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AlarmEdit.class);
                c.setCurrentAlarmID(position); //sets the current alarmID so that the correct data is used
                view.getContext().startActivity(intent);
            }
        });

        switch (c.getAlarms().get(position).getRepeat()){
            case 1:
                alarmRepeat.setText("Repeat: Every Hour");
                break;
            case 2:
                alarmRepeat.setText("Repeat: Every Day");
                break;
            case 3:
                alarmRepeat.setText("Repeat: Every Week");
                break;
            default:
                alarmRepeat.setText("Repeat: None");
                break;
        }

        return convertView;
    }
}
