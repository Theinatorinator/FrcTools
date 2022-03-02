package com.frcpackagemanager;


import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        ModeSelect modeSelect = new ModeSelect();
        CompetitionMode competitionMode = new CompetitionMode();
        System.out.println("This is the team 7450 competition tools");
        if (modeSelect.ModeSelectUI() == 1) {
            competitionMode.CompetitionModeInit();
        }
    }
}
