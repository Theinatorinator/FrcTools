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
        String driverStationLocation = prop.getProperty("driverStationLocation");
        String robotRadioSSID = prop.getProperty("robotRadioSSID");
        String robotRadioPSK = prop.getProperty("robotRadioPSK");
        NetworkConnection(robotRadioSSID, robotRadioPSK);
    }
    public void NetworkConnection(String ssid, String password) {
        String powerShellCommandBase = "netsh wlan connect ssid=\"";
        String finalPowerShellCommand = powerShellCommandBase + ssid + "\" " + "key=\"" + password + "\"";
        System.out.println(finalPowerShellCommand);
        PowerShellResponse response = PowerShell.executeSingleCommand(finalPowerShellCommand);

    }

    public void RunDriverStation(String driveStation) {
        String driverStationLocation = driveStation;
        try {
            Runtime.getRuntime().exec(driverStationLocation);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(3);
        }
    }

}

