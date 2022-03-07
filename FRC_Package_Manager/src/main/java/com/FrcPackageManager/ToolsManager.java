package com.FrcPackageManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ToolsManager {
    private final Scanner scanner = new Scanner(System.in);
    private boolean downloading = false;
    ModeSelect modeSelect = new ModeSelect();
    Logger logger = Logger.getLogger("RunTimeLog");
    String logFileLocation = System.getenv("APPDATA") + "\\FRCTools\\Logs\\Log";
    public void ToolsManagerInit() {
        try {
            FileHandler fileHandler = new FileHandler(logFileLocation);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch(java.io.IOException ex) {
            ex.printStackTrace();
        }
        try {
            String directoryLocation = System.getenv("APPDATA") + "\\Roaming\\FRCTools\\ToolsManager";
            Files.createDirectories(Paths.get(directoryLocation));
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "IO EXCEPTION", e);
        }
        ToolsManagerMain();
    }
    private void ToolsManagerMain() {
        GetDownloadChoices();
    }
    private void DownloadFiles(String websiteLocation, String outputLocation)  {
        //System.out.println("DOWNLOADING");
        URL website = null;
        downloading = true;
        //System.out.println(downloading);
        try {
            website = new URL(websiteLocation);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "MALFORMED URL", e);
        }
        ReadableByteChannel rbc = null;
        try {
            assert website != null;
            rbc = Channels.newChannel(website.openStream());
            //System.out.println("rbc");
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "CONNECTION TO WEBSITE FAILURE", e);
        }
        FileOutputStream fos = null;
        try {
            //System.out.println("fos");
            fos = new FileOutputStream(outputLocation);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "FILE NOT FOUND", e);
            e.printStackTrace();
        }

        try {
            new Thread(runnable).start();
            System.out.print("DOWNLOADING: ");
            assert fos != null;
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO EXCEPTION", e);
            e.printStackTrace();
        }
        downloading = false;
        //System.out.println(downloading);
        System.out.println(" DOWNLOAD COMPLETE");
    }

    private void GetDownloadChoices() {
        boolean downloadGameManual = false;
        boolean downloadWPI = false;
        String userInput;
        String userInput1;
        do {
            System.out.print("would you like to download the game manual (y/n): ");
            userInput = scanner.next().trim().toUpperCase(Locale.ROOT);

        } while (!userInput.matches("N") && !userInput.matches("Y"));
        do {
            System.out.print("would you like to download the 2022 wpilibs (y/n): ");
            userInput1 = scanner.next().trim().toUpperCase(Locale.ROOT);

        } while (!userInput1.matches("N") && !userInput1.matches("Y"));

            if (userInput.matches("Y")) {
                downloadGameManual = true;
            }
            if (userInput1.matches("Y")) {
                downloadWPI = true;
            }
        //System.out.println(userInput);
        //System.out.println(userInput1);
        //System.out.println(downloadGameManual);
        //System.out.println(downloadWPI);
        //System.out.println("test");
        DownloadFromUI(downloadWPI, downloadGameManual);

    }

    private void GetGameManual() {
        //System.out.print("test");
        String gameManualLocation = "https://firstfrc.blob.core.windows.net/frc2022/Manual/2022FRCGameManual.pdf";
        String downloadOutput = System.getenv("APPDATA") +"\\FRCTools\\ToolsManager\\Downloads\\2022GameManual.pdf";
        System.out.println("Getting manual");
        DownloadFiles(gameManualLocation, downloadOutput);
    }

    private void  GetWpiLib() {
        String wpiLibLocation = "https://github.com/wpilibsuite/allwpilib/releases/download/v2022.4.1/WPILib_Windows64-2022.4.1.iso";
        String downloadOutput = System.getenv("APPDATA") + "\\FRCTools\\ToolsManager\\Downloads\\WPILib_Windows64-2022.4.1.iso";
        System.out.println("getting WPILIBS");
        DownloadFiles(wpiLibLocation, downloadOutput);

    }


    Runnable runnable = this::ProgressBar;
    private void ProgressBar() {
            while (downloading) {
                System.out.print(".");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logger.log(Level.SEVERE, "INTERRUPTED SLEEP", e);
                }
            }
    }

    private void DownloadFromUI(boolean wpi, boolean man ) {
        String wpiLibs = String.valueOf(wpi);
        String manual = String.valueOf(man);

        switch (wpiLibs){
            case "true" :
                GetWpiLib();
                break;
            case "false" :
                break;
        }

        switch (manual) {
            case "true" :
                GetGameManual();
                break;
            case "false" :
                break;
        }
        //UIForWPI();
        modeSelect.ModeSelectUI();
    }


    /*
    private void RunWPI() {
        String wpiLocation = "C:\\Program Files\\FRC_Tools\\WPILib_Windows64-2022.4.1.iso";
        try {
            Runtime.getRuntime().exec(wpiLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void UIForWPI() {
        String userInput;
        do {
            System.out.print("Would you like to run WPILIBS installer (y/n):");
            userInput = scanner.next().trim().toUpperCase(Locale.ROOT);
        } while (!userInput.matches("N") && !userInput.matches("Y"));
        if (userInput.equals("Y")) {
            RunWPI();
        }
        modeSelect.ModeSelectUI();
    }
     */
}
