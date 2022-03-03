package com.FrcPackageManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class ToolsManager {

    public void ToolsManagerInit() {
        //File theDir = new File("CSIDL_DESKTOP\\tools");
        // if (!theDir.exists()){
        //    theDir.mkdirs();
        // }
        ToolsManagerMain();
    }
    private void ToolsManagerMain() {
        GetGameManual();
    }
    private void DownloadFiles(String websiteLocation, String outputLocation)  {
        URL website = null;
        try {
            website = new URL(websiteLocation);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ReadableByteChannel rbc = null;
        try {
            rbc = Channels.newChannel(website.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outputLocation);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ToolsManagerMain();
    }

    private void GetGameManual() {
        String gameManualLocation = "https://firstfrc.blob.core.windows.net/frc2022/Manual/2022FRCGameManual.pdf";
        String downloadOutput = "C:\\Program Files\\FRC_Tools\\2022GameManual.pdf";
        DownloadFiles(gameManualLocation, downloadOutput);
    }
}
