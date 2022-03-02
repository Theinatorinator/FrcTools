package com.logan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static  void main(String[] args) {
        getProperties();
    }
    public static void getProperties(String[] args) {
        Properties configOptions = new Properties();
        String filename = "";
        try (FileInputStream fis = new FileInputStream((filename))) {
            configOptions.load(fis);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex ) {
            ex.printStackTrace();
        }
        System.out.println(configOptions.getProperty("app.name"));
        System.out.println(configOptions.getProperty("app.version"));
        String driveSpeedMax = configOptions.getProperty("robot.config.driveBase.driveSpeedMax");
        System.out.println(driveSpeedMax);

    }
}

