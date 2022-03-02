package com.FrcPackageManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class ConfigurationMode {
    public void SetConfig() {
        String propFileLocation = "FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/Config.properties";
        //Set up file loading
        Properties prop = new Properties();
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
        //set up other classes
        ModeSelect modeSelect = new ModeSelect();
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

        System.out.println("Configuration Complete!!");
        prop.setProperty("configDone", "true");

        try {
            //stores everything in memory into the properties file
            FileOutputStream out = new FileOutputStream(propFileLocation);
            prop.store(out, null);
        }  catch (java.io.IOException ex) {
            ex.printStackTrace();
        }

        modeSelect.ModeSelectUI();
    }
}
