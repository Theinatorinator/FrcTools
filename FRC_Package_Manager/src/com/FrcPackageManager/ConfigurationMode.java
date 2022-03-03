package com.FrcPackageManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class ConfigurationMode {
    private final Properties prop = new Properties();
    private final String propFileLocation = "out/production/FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/Config.properties";
    private final ModeSelect modeSelect = new ModeSelect();

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
        System.out.println("Configuration Complete!!");
        prop.setProperty("configDone", "true");
        //configure props for music mode
        MusicModeConfig();
        //store all the props in a file
        ConfigStore();
        //go to mode select/main screen
        modeSelect.ModeSelectUI();
    }

    private void ConfigModeGetUI() {
        //Set up Configs
        Scanner scanner = new Scanner(System.in);
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
    }
    private void MusicModeConfig() {
        prop.setProperty("song1", "out/production/FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/music1.wav");
        prop.setProperty("song2", "out/production/FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/music2.wav");
        prop.setProperty("song3", "out/production/FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/music3.wav");
        prop.setProperty("song4", "out/production/FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/music4.wav");
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
