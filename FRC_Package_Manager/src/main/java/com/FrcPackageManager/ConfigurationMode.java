package com.FrcPackageManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

public class ConfigurationMode {
    private final Properties prop = new Properties();
    private final String propFileLocation = "C:\\Program Files\\FRC_Tools\\Config\\Config.properties";
    private boolean musicModeConfigUI = false;
    Scanner scanner = new Scanner(System.in);
    ModeSelect modeSelect = new ModeSelect();

    public void ConfigModeInit() {
        try {
            FileOutputStream out = new FileOutputStream(propFileLocation);
            prop.store(out, null);
        } catch (java.io.FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
        //Load the file, catching any exceptions
        try {
            prop.load(new FileInputStream(propFileLocation));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
        SetConfigMain();
    }
    public void SetConfigMain() {
        //Get UI
        ConfigModeGetUI();
        //confirm to user
        //set done proper to done
        //config music mode with base
        MusicModeConfig();
        //if user wants to config music mode custom then they are entered into it
        if (musicModeConfigUI == true) {
            MusicModeConfigUI();
        }
        System.out.println("Configuration Complete!!");
        prop.setProperty("configDone", "true");
        //store all the props in a file
        ConfigStore();
        //go to mode select/main screen
        modeSelect.ModeSelectUI();
    }
    private void MusicModeConfigUI() {
        String userInput = "";
        System.out.println("Welcome to the Music Mode configuration utility");
        System.out.println("Here you wil configure music mode");
        System.out.print("Music File one Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("music1Name", userInput );
        userInput = "";
        System.out.print("Music File two Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("music2Name", userInput );
        userInput = "";
        System.out.print("Music File three Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("music3Name", userInput );
        userInput = "";
        System.out.print("Music File four Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("music4Name", userInput );
        userInput = "";
        System.out.print("Music File five Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("music5Name", userInput );
        userInput = "";
        System.out.print("Music File six Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("music6Name", userInput );
        userInput = "";
        System.out.print("Music File seven Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("music7Name", userInput );
        userInput = "";
        System.out.print("Music File eight Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("music8Name", userInput );
        userInput = "";
        System.out.print("Music File nine Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("music9Name", userInput );
        userInput = "";
        System.out.print("Music File ten Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("music10Name", userInput );
        userInput = "";

    }
    private void ConfigModeGetUI() {
        //Set up Configs
        String userInput = "";
        System.out.println("Welcome to the configuration utility");
        System.out.println("Here you wil configure robot properties and team name");
        System.out.print("Team Number: ");
        userInput = scanner.next().trim();
        prop.setProperty("teamNumber", userInput );
        userInput = "";
        System.out.print("Team Name: ");
        userInput = scanner.next().trim();
        prop.setProperty("teamName", userInput);
        userInput= "";
        System.out.print("Robot ip address: ");
        userInput = scanner.next().trim();
        prop.setProperty("robotIPAdder", userInput);
        userInput = "";
        System.out.print("Wifi Profile Name, this is what you click on in the wifi gui, example 7540: ");
        userInput = scanner.next().trim();
        prop.setProperty("profileName", userInput);
        userInput = "";
        System.out.print("Location of drive station exe: ");
        scanner.nextLine();
        userInput = scanner.nextLine();
        prop.setProperty("driverStationLocation", userInput);
        userInput = "";
        do {
            System.out.print("Would you like to enter Music Mode config: ");
            userInput = scanner.next().trim().toUpperCase(Locale.ROOT);
        } while (userInput.matches("Y") && userInput.matches("N")); {
            if (userInput.matches("Y")) {
                musicModeConfigUI = true;
            }
        }


    }
    private void MusicModeConfig() {
        prop.setProperty("song1", "C:\\Program Files\\FRC_Tools\\Music\\music1.wav");
        prop.setProperty("song2", "C:\\Program Files\\FRC_Tools\\Music\\music2.wav");
        prop.setProperty("song3", "C:\\Program Files\\FRC_Tools\\Music\\music3.wav");
        prop.setProperty("song4", "C:\\Program Files\\FRC_Tools\\Music\\music4.wav");
        prop.setProperty("song5", "C:\\Program Files\\FRC_Tools\\Music\\music5.wav");
        prop.setProperty("song6", "C:\\Program Files\\FRC_Tools\\Music\\music6.wav");
        prop.setProperty("song7", "C:\\Program Files\\FRC_Tools\\Music\\music7.wav");
        prop.setProperty("song8", "C:\\Program Files\\FRC_Tools\\Music\\music8.wav");
        prop.setProperty("song9", "C:\\Program Files\\FRC_Tools\\Music\\music9.wav");
        prop.setProperty("song10", "C:\\Program Files\\FRC_Tools\\Music\\music10.wav");
        prop.setProperty("music1Name", "Air Raid Siren");
        prop.setProperty("music2Name", "Fresh Friday");
        prop.setProperty("music3Name", "GIGA CHAD");
        prop.setProperty("music4Name", "modern desert music");
        prop.setProperty("music5Name", "Dies Eire");
        prop.setProperty("music6Name", "music1");
        prop.setProperty("music7Name", "music1");
        prop.setProperty("music8Name", "music1");
        prop.setProperty("music9Name", "music1");
        prop.setProperty("music10Name", "music1");

    }
    private void ConfigStore() {
        try {
            //stores everything in memory into the properties file
            FileOutputStream out = new FileOutputStream(propFileLocation);
            prop.store(out, null);
        }  catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
    }
}
