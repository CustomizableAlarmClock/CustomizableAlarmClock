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

/**
 * Created by Derek on 4/27/2018.
 * Controller to handle data
 */
public class Controller extends Application {
    private ArrayList<Alarm> alarms = new ArrayList<>();
    private int currentAlarmID;
    private int currentSoundID;
    private File f;

    /**
     * Adds an alarm to the Alarm ArrayList
     * @param a the alarm to be added
     */
    public void addAlarm(Alarm a){
        alarms.add(a);
    }

    /**
     * Removes an alarm from the ArrayList
     * @param i the index of the Alarm to be removed
     */
    public void removeAlarm(int i){
        alarms.remove(i);
    }

    /**
     * Gets the ArrayList of Alarms
     * @return the ArrayList of Alarms
     */
    public ArrayList<Alarm> getAlarms(){
        return alarms;
    }

    /**
     * Gets the current Alarm
     * @return the current Alarm
     */
    public int getCurrentAlarmID(){
        return currentAlarmID;
    }

    /**
     * Sets the current Alarm
     * @param alarmID the current Alarm
     */
    public void setCurrentAlarmID(int alarmID){
        currentAlarmID = alarmID;
    }

    /**
     * Gets the current Sound
     * @return the current Sound
     */
    public int getCurrentSoundID(){
        return currentSoundID;
    }

    /**
     * Sets the current Sound
     * @param soundID the current Sound
     */
    public void setCurrentSoundID(int soundID){
        currentSoundID = soundID;
    }

    /**
     * Gets the file name
     * @return the file name
     */
    public static String getFileName(){
        String filename;
        filename = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "data.txt";
        return filename;
    }

    /**
     * Determines the length of the text file
     * @return the length of the text file
     */
    private int getFileLength() {
        FileInputStream fileInputStream;
        BufferedReader inputReader = null;
        try {
            fileInputStream = new FileInputStream(f);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            inputReader = new BufferedReader(inputStreamReader);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        int i = 0;
        try {
            if (inputReader != null) {
                while (inputReader.readLine() !=null){
                    i++;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(inputReader!=null) {
                    inputReader.close();
                }
            }
            catch (IOException e) {
                Log.d("IOException","IOException");
            }
        }
        return i;
    }

    /**
     * Reads the data from the text file
     */
    public void getDataFromFile(){
        f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "data.txt");
        Log.d("asdffff","we");
        if(!f.exists()){
            Log.d("CREATENEW", "createnewfile");
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            while (i<getFileLength()){
                Log.d("Read",String.valueOf(i));
                Log.d("Read2",String.valueOf(getFileLength()));
                line = inputReader.readLine();
                if(line==null){
                    break;
                }
                switch (line) {
                    case "q35Kz":
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
                        break;
                    case "KhC43":
                        line = inputReader.readLine();
                        String soundName = line;
                        line = inputReader.readLine();
                        Log.d("222111", line);
                        String fileName = line;
                        line = inputReader.readLine();
                        Log.d("22222", line);
                        int soundID = Integer.parseInt(line);
                        i = i + 3;
                        Sound s = new Sound(soundName, fileName, soundID);
                        sounds.add(s);
                        break;
                    case "kSd41911":
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
                        break;
                }
                Alarm a = new Alarm(alarmName, sounds, alarmTime, timeLeft, repeat, isActive, snoozeActive, alarmID);
                alarms.add(a);
            }
            if (inputReader != null) {
                inputReader.close();
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates text file if it does not exist yet
     * Writes data to text file
     * @param filename the name of the file
     */
    public void writeToFile (String filename){
        BufferedWriter outputWriter;
        try {
            outputWriter = new BufferedWriter(new FileWriter(filename, false));
            for(int i = 0; i<alarms.size(); i++){
                Log.d("i1",String.valueOf(i));
                Log.d("Alarm Size Controller", String.valueOf(alarms.size()));
                outputWriter.write("q35Kz");
                outputWriter.newLine();
                outputWriter.write(alarms.get(i).getAlarmName());
                outputWriter.newLine();
                Log.d("i2",String.valueOf(i));
                outputWriter.write(String.valueOf(alarms.get(i).getTimeLeft()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getRepeat()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getIsActive()));
                outputWriter.newLine();
                outputWriter.write(String.valueOf(alarms.get(i).getSnoozeActive()));
                outputWriter.newLine();
                Log.d("i3",String.valueOf(i));
                outputWriter.write(String.valueOf(alarms.get(i).getId()));
                outputWriter.newLine();
                Log.d("i4",String.valueOf(i));
                try {
                    for (int j = 0; j < alarms.get(i).getSounds().size(); j++) {
                        Log.d("i5",String.valueOf(i));
                        Log.d("write", String.valueOf(j));
                        Log.d("write2", String.valueOf(alarms.get(i).getSounds().size()));
                        outputWriter.write("KhC43");
                        outputWriter.newLine();
                        outputWriter.write(alarms.get(i).getSounds().get(j).getSoundName());
                        outputWriter.newLine();
                        outputWriter.write(alarms.get(i).getSounds().get(j).getFileName());
                        outputWriter.newLine();
                        outputWriter.write(String.valueOf(alarms.get(i).getSounds().get(j).getId()));
                        outputWriter.newLine();
                    }
                }
                catch (IndexOutOfBoundsException e){
                    Log.d("Alarm Size", String.valueOf(alarms.size()));
                    Log.d("i",String.valueOf(i));
                    Log.d("Sound Size",String.valueOf(alarms.get(i).getSounds().size()));

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
            outputWriter.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}