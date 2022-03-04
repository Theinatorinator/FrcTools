package com.FrcPackageManager;

import java.io.File;
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

public class ToolsManager {
    private final String directoryLocation = "C:\\Program Files\\FRC_Tools\\";
    private final Scanner scanner = new Scanner(System.in);
    private boolean downloading = false;
    ModeSelect modeSelect = new ModeSelect();
    public void ToolsManagerInit() {
        try {
            Files.createDirectories(Paths.get(directoryLocation));
        } catch (IOException e) {
            e.printStackTrace();
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
        }
        ReadableByteChannel rbc = null;
        try {
            rbc = Channels.newChannel(website.openStream());
            //System.out.println("rbc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            //System.out.println("fos");
            fos = new FileOutputStream(outputLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            new Thread(runnable).start();
            System.out.print("DOWNLOADING: ");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
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
            userInput1 = "";
            userInput1 = scanner.next().trim().toUpperCase(Locale.ROOT);

        } while (!userInput1.matches("N") && !userInput1.matches("Y"));

            if (userInput == "Y") {
                downloadGameManual = true;
            }
            if (userInput1 == "Y") {
                downloadWPI = true;
            }

        DownloadFromUI(downloadWPI, downloadGameManual);

    }

    private void GetGameManual() {
        //System.out.print("test");
        String gameManualLocation = "https://firstfrc.blob.core.windows.net/frc2022/Manual/2022FRCGameManual.pdf";
        String downloadOutput = "C:\\Program Files\\FRC_Tools\\2022GameManual.pdf";
        DownloadFiles(gameManualLocation, downloadOutput);
    }

    private void  GetWpiLib() {
        String wpiLibLocation = "https://github.com/wpilibsuite/allwpilib/releases/download/v2022.4.1/WPILib_Windows64-2022.4.1.iso";
        String downloadOutput = "C:\\Program Files\\FRC_Tools\\bensound-acousticbreeze.mp3";
        DownloadFiles(wpiLibLocation, downloadOutput);

    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
           ProgressBar();
        }
    };
    private void ProgressBar() {
            while (downloading == true) {
                System.out.print(".");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    private void DownloadFromUI(boolean wpi, boolean man ) {

        if (wpi = true) {
            if (man) {
                DownloadAll();
            } else if (wpi = true) {
                GetWpiLib();
            }
        }
        if (man = true) {
            GetGameManual();
        }
    }

    private void DownloadAll() {
        GetWpiLib();
        GetGameManual();
        System.out.printf("ALL DOWNLOAD COMPLETE");
        modeSelect.ModeSelectUI();
    }
}
