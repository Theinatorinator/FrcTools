//Competition mode automatically
package com.FrcPackageManager;


import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CompetitionMode {
    Logger logger = Logger.getLogger("RunTimeLog");
    String logFileLocation = System.getenv("APPDATA") + "\\FRCTools\\Logs\\Log";
    public void CompetitionModeMain() {
        String propFileLocation = System.getenv("APPDATA") + "\\FRCTools\\Properties\\Config.properties";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Competition mode initializing");
        String userInput = "";
       //prepare file loading
        Properties prop = new Properties();
        //Load the file, catching any exceptions
        try {
            FileHandler fileHandler = new FileHandler(logFileLocation);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch(java.io.IOException ex) {
            ex.printStackTrace();
        }
        try {
            prop.load(new FileInputStream(propFileLocation));
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Couldn't load properties file", e);
            System.exit(2);
        }
        //Load each individual variable form the config file
        String profileName = prop.getProperty("profileName");
        String robotIPAdder = prop.getProperty("robotIPAdder");
        NetworkConnection(profileName);
        RunDriverStation(prop.getProperty("driverStationLocation"));

    }
    private void NetworkConnection(String profileName) {
        //Creates and then calls a powershell command which connects the computer to the specified network profile, need to add a network profile config in the config mode which will be added later
        System.out.println("CONNECTING TO ROBOT NETWORK SPECIFIED IN CONFIG FILE");
        String powerShellCommand = "netsh wlan connect name=" + profileName;
        //call the powershell command
        PowerShellResponse response = PowerShell.executeSingleCommand(powerShellCommand);
        System.out.println(response);
    }

    private void RunDriverStation(String driveStation) {
        //this opens the FRC Diver Station based on the location of the exe put into the Config file
        //System.out.println(driveStation);
        try {
            Runtime.getRuntime().exec(driveStation);
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, "couldn't find/launch the driver station ensure that the path is correct", ex);
            System.exit(3);
        }
    }


}

