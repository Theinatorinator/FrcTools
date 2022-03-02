package com.FrcPackageManager;


public class Main {

    public static void main(String[] args) {
        ModeSelect modeSelect = new ModeSelect();
        CompetitionMode competitionMode = new CompetitionMode();
        System.out.println("This is the team 7450 competition tools");
        //calls mode select ui, which puts you into the application mode
        modeSelect.ModeSelectUI();
    }
}
