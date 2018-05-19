package com.example.derek.customizablealarmclock;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Creates a custom adapter for the AllAlarms ListView
 * https://www.youtube.com/watch?v=YaIcwyIF2-o
 */
public class Adapter extends ArrayAdapter<String> {
    Controller c;

    /**
     * Constructs the Adapter
     * @param context the Context
     * @param records the ArrayList of Alarms
     * @param c the Controller
     */
    Adapter(Context context, ArrayList<String> records, Controller c){
        super(context, 0,  records);
        this.c = c;
    }

    /**
     * Overrides the getView method to display a custom ListView in the AllAlarms page
     * @param position the index of an Alarm in the ArrayList of Alarms
     * @param convertView a View
     * @param parent a ViewGroup
     * @return the View to be displayed in the custom ListView
     */
    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent){
        //Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_custom, parent, false);
        }

        final TextView alarmName = convertView.findViewById(R.id.TextViewListItem);
        final Switch aSwitch = convertView.findViewById(R.id.SwitchAlarmActive);
        final TextView alarmTime = convertView.findViewById(R.id.TextViewAlarmTime);
        final TextView alarmRepeat = convertView.findViewById(R.id.TextViewDisplayRepeat);

        //displays the time that the Alarm goes off
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

        //sets the Switch
        if(c.getAlarms().get(position).getIsActive()){
            aSwitch.setText("On");
        }
        else {
            aSwitch.setText("Off");
        }
        aSwitch.setChecked(c.getAlarms().get(position).getIsActive());
        aSwitch.setOnClickListener(new View.OnClickListener() {
            /**
             * Toggles the Switch when clicked
             * @param view View of the ListView
             */
            @Override
            public void onClick(View view) {
                c.getAlarms().get(position).setActive(aSwitch.isChecked());
                //Enables the Alarm
                if(aSwitch.isChecked() && c.getAlarms().get(position).getSounds().size()>0){
                    aSwitch.setText("On");
                    SetAlarm setAlarm = new SetAlarm(getContext(), c.getAlarms().get(position).getRepeat(), c.getAlarms().get(position).getTimeLeft(), c.getCurrentAlarmID());
                    setAlarm.setAlarm();
                }
                //Alarm enable attempted, but no sounds exist
                else if(aSwitch.isChecked() && c.getAlarms().get(position).getSounds().size()==0){
                    Toast.makeText(view.getContext(), "No Sounds in Alarm", Toast.LENGTH_LONG).show();
                    aSwitch.setChecked(false);
                }
                //Disables the Alarm
                else {
                    aSwitch.setText("Off");
                    c.getAlarms().get(position).setActive(false);
                    Toast.makeText(view.getContext(), "Alarm Cancelled", Toast.LENGTH_LONG).show();
                }
            }
        });

        alarmName.setOnClickListener(new View.OnClickListener(){
            /**
             * Goes to the AlarmEdit page if the user taps on the alarm name part of the ListView
             * @param v the View
             */
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), AlarmEdit.class);
                c.setCurrentAlarmID(position); //sets the current alarmID so that the correct data is used
                v.getContext().startActivity(intent);
            }
        });

        alarmTime.setOnClickListener(new View.OnClickListener() {
            /**
             * Goes to the AlarmEdit page if the user taps on the time part of the ListView
             * @param view the View
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AlarmEdit.class);
                c.setCurrentAlarmID(position); //sets the current alarmID so that the correct data is used
                view.getContext().startActivity(intent);
            }
        });

        alarmRepeat.setOnClickListener(new View.OnClickListener() {
            /**
             * Goes to the AlarmEdit page if the user taps on the repeat part of the ListView
             * @param view the View
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AlarmEdit.class);
                c.setCurrentAlarmID(position); //sets the current alarmID so that the correct data is used
                view.getContext().startActivity(intent);
            }
        });

        //Sets the text on the ListView to match the repeat setting of the Alarm
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
