package com.example.derek.customizablealarmclock;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Derek on 4/27/2018.
 * Controller to handle data
 */

public class Controller extends Application {
    private ArrayList<Alarm> alarms = new ArrayList<>();
    private int currentAlarmID;
    private int currentSoundID;

    public void addAlarm(Alarm a){
        writeToFile(getFileName());
        alarms.add(a);
    }

    public void removeAlarm(int i){
        writeToFile(getFileName());
        alarms.remove(i);
    }

    public void updateAlarm(Alarm a){

    }

    public ArrayList<Alarm> getAlarms(){
        return alarms;
    }

    public int getCurrentAlarmID(){
        return currentAlarmID;
    }

    public void setCurrentAlarmID(int alarmID){
        writeToFile(getFileName());
        currentAlarmID = alarmID;
    }

    public int getCurrentSoundID(){
        return currentSoundID;
    }

    public void setCurrentSoundID(int soundID){
        writeToFile(getFileName());
        currentSoundID = soundID;
    }

    //creates file name
    private static String getFileName(){
        String filename;
        //String dir = getEnvironment().getFilesDir().getAbsolutePath();
        filename = Environment.getExternalStorageDirectory().getAbsolutePath()+"data.txt";
        return filename;
    }

    //determines how many data entries there are
    private int getFileLength(String filename) {
        FileInputStream fileInputStream;
        BufferedReader inputReader = null;
        try {
            fileInputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            inputReader = new BufferedReader(inputStreamReader);
            //inputReader = new BufferedReader (new InputStreamReader(new FileInputStream(filename)));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/" + UUID.randomUUID().toString(), "/data.txt");
        }

        String line = "";
        int i = 0;
        try {
            while ((line = inputReader.readLine()) !=null){
                i++;
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if(inputReader!=null) {
                    inputReader.close();
                }
            }
            catch (IOException e) {
                Log.d("IOException","IOException");
            }

            return i;
        }
    }



    public void getDataFromFile(){
        BufferedReader inputReader = null;
        try {
            inputReader = new BufferedReader (new InputStreamReader (new FileInputStream (getFileName())));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String line;
        int i = 0;
        String alarmName = "";
        long timeLeft = 0;
        int repeat = 0;
        boolean isActive = false;
        boolean snoozeActive = false;
        int alarmID = 0;
        ArrayList<Sound> sounds = new ArrayList<>();
        AlarmTime alarmTime = new AlarmTime();

        try{
            while (i<getFileLength(getFileName())){
                line = inputReader.readLine();
                if(line==null){
                    break;
                }
                if(line.equals("q35Kz")){
                    line = inputReader.readLine();
                    alarmName = line;
                    line = inputReader.readLine();
                    timeLeft = Long.parseLong(line);
                    line = inputReader.readLine();
                    repeat = Integer.parseInt(line);
                    line = inputReader.readLine();
                    isActive = Boolean.parseBoolean(line);
                    line = inputReader.readLine();
                    snoozeActive = Boolean.parseBoolean(line);
                    line = inputReader.readLine();
                    alarmID = Integer.parseInt(line);
                    i = i + 6;
                }
                else if(line.equals("KhC43")){
                    line = inputReader.readLine();
                    String soundName = line;
                    line = inputReader.readLine();
                    String fileName = line;
                    line = inputReader.readLine();
                    int soundID = Integer.parseInt(line);
                    i = i + 3;
                    Sound s = new Sound(soundName, fileName, soundID);
                    sounds.add(s);
                }
                else if(line.equals("kSd41911")){
                    line = inputReader.readLine();
                    int year = Integer.parseInt(line);
                    line = inputReader.readLine();
                    int month = Integer.parseInt(line);
                    line = inputReader.readLine();
                    int day = Integer.parseInt(line);
                    line = inputReader.readLine();
                    int hour = Integer.parseInt(line);
                    line = inputReader.readLine();
                    int minute = Integer.parseInt(line);
                    i = i + 5;
                    alarmTime = new AlarmTime(year, month, day, hour, minute);
                }


                Alarm a = new Alarm(alarmName, sounds, alarmTime, timeLeft, repeat, isActive, snoozeActive, alarmID);
                alarms.add(a);


            }
            inputReader.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    //creates file and writes data to file
    public void writeToFile (String filename){
        BufferedWriter outputWriter;
        try {
            outputWriter = new BufferedWriter(new FileWriter(filename, true));
            for(int i = 0; i<alarms.size(); i++){
                outputWriter.write("q35Kz");
                outputWriter.newLine();
                outputWriter.write(alarms.get(i).getAlarmName());
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getTimeLeft()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getRepeat()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getIsActive()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getSnoozeActive()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getId()));
                outputWriter.newLine();
                for(int j = 0; j<alarms.get(i).getSounds().size(); i++){
                    outputWriter.write("KhC43");
                    outputWriter.newLine();
                    outputWriter.write(alarms.get(i).getSounds().get(j).getSoundName());
                    outputWriter.newLine();
                    outputWriter.write(alarms.get(i).getSounds().get(j).getFileName());
                    outputWriter.newLine();
                    outputWriter.write(alarms.get(i).getSounds().get(j).getId());
                    outputWriter.newLine();
                }
                outputWriter.write("kSd41911");
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getTime().getYear()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getTime().getMonth()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getTime().getDay()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getTime().getHour()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getTime().getMinute()));
                outputWriter.newLine();

            }

            outputWriter.flush();
            outputWriter.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
