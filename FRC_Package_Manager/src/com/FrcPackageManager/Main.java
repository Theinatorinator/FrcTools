package com.FrcPackageManager;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Main {

    public static void main(String[] args) {
        String propFileLocation = "out/production/FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/Config.properties";
        ModeSelect modeSelect = new ModeSelect();
        CompetitionMode competitionMode = new CompetitionMode();
        ConfigurationMode configurationMode = new ConfigurationMode();
        Properties prop = new Properties();
        //set up prop file loading
        //Load the file, catching any exceptions
        try {
            prop.load(new FileInputStream(propFileLocation));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
        String configDoneString = prop.getProperty("configDone");
        String teamNumber = prop.getProperty("teamNumber");
        if (configDoneString.matches("true")) {
            System.out.println("Hello team " + teamNumber + " welcome to the frc utilities");
            modeSelect.ModeSelectUI();
        }
        if (configDoneString.matches("false")) {
            configurationMode.SetConfig();
        }
    }
}
