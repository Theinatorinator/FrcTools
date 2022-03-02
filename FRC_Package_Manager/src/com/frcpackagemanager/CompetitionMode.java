package com.frcpackagemanager;


import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CompetitionMode {
    public void CompetitionModeInit() {
        System.out.println("Competition mode initializing");
       //prepare file loading
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("out/production/FRC_Package_Manager/com/frcpackagemanager/CompetitionModeConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //File loading
        String profileName = prop.getProperty("profileName");
        NetworkConnection(profileName);
        RunDriverStation(prop.getProperty("driverStationLocation"));
    }
    public void NetworkConnection(String profileName) {
        System.out.println("CONNECTING TO ROBOT NETWORK SPECIFIED IN CONFIG FILE");
        String powerShellCommand = "netsh wlan connect name=" + profileName;
        PowerShellResponse response = PowerShell.executeSingleCommand(powerShellCommand);
        System.out.println(response);
    }

    public void RunDriverStation(String driveStation) {
        //System.out.println(driveStation);
        try {
            Runtime.getRuntime().exec(driveStation);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(3);
        }
    }

}

