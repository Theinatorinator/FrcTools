package com.FrcPackageManager;

import java.util.Locale;
import java.util.Scanner;
//ask the user what mode to enter, currently this only allows from competition mode but other modes such as programing/updating are in the works
public class ModeSelect {
    public void ModeSelectUI() {
        CompetitionMode competitionMode = new CompetitionMode();
        ConfigurationMode configurationMode = new ConfigurationMode();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.print("PLease select your mode: ");
            userInput = scanner.next().trim().toUpperCase(Locale.ROOT);

        } while (!userInput.matches("0") && !userInput.matches("1") && !userInput.matches("2") && !userInput.matches("3") && !userInput.matches("\\?") && !userInput.matches("HELP")); {
            switch (userInput) {
                //quit on input of 0
                case "0" :
                    Quit();
                    break;

                //temp, probably configuration
                case "1" :
                    configurationMode.SetConfig();
                    break;

                //temp, probably going to be updated
                case "2" : System.out.println("temp");
                    break;

                //Competition mode
                case "3" : competitionMode.CompetitionModeMain();
                    break;

                //help
                case "?": Help();
                    break;
                case "/?" : Help();
                    break;
                case "HELP" : Help();
                    break;

            }
        }

    }
    public void Quit() {
        System.out.println("Exiting");
        System.exit(0);
    }
    public void Help() {
        System.out.println("0 = quit");
        System.out.println("1= temp");
        System.out.println("2= temp");
        System.out.println("3= CompetitionMode");
        System.out.println("? = help");
        ModeSelectUI();
    }
}
