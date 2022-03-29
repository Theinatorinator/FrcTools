package com.FrcPackageManager;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    public static void main(String[] args) {
        String propFileLocation = System.getenv("APPDATA") + "\\FRCTools\\Properties\\Config.properties";
        ModeSelect modeSelect = new ModeSelect();
        ConfigurationMode configurationMode = new ConfigurationMode();
        Properties prop = new Properties();
        Logger logger = Logger.getLogger("RunTimeLog");

        //set up prop file loading
        //Load the file, catching any exceptions
        String logFileLocation = System.getenv("APPDATA") + "\\FRCTools\\Logs\\Log";
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
            logger.info("Failed to find properties file location");
            System.exit(3);
        }
        String configDoneString = prop.getProperty("configDone");
        String teamNumber = prop.getProperty("teamNumber");
        if (configDoneString.matches("true")) {
            //logger.info("Success, launching frcTools mode select");
            System.out.println("Hello team " + teamNumber + " welcome to frc tools");
            System.out.println("███████╗██████╗░░█████╗░        ████████╗░█████╗░░█████╗░██╗░░░░░░██████╗");
            System.out.println("██╔════╝██╔══██╗██╔══██╗        ╚══██╔══╝██╔══██╗██╔══██╗██║░░░░░██╔════╝");
            System.out.println("█████╗░░██████╔╝██║░░╚═╝        ░░░██║░░░██║░░██║██║░░██║██║░░░░░╚█████╗░");
            System.out.println("██╔══╝░░██╔══██╗██║░░██╗        ░░░██║░░░██║░░██║██║░░██║██║░░░░░░╚═══██╗");
            System.out.println("██║░░░░░██║░░██║╚█████╔╝        ░░░██║░░░╚█████╔╝╚█████╔╝███████╗██████╔╝");
            System.out.println("╚═╝░░░░░╚═╝░░╚═╝░╚════╝░        ░░░╚═╝░░░░╚════╝░░╚════╝░╚══════╝╚═════╝░");
            modeSelect.ModeSelectUI();
        }
        if (configDoneString.matches("false")) {
            logger.info("Entering auto config");
            configurationMode.ConfigModeInit();
        }
    }

}
