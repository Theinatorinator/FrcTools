package com.FrcPackageManager;



import java.util.Locale;
import java.util.Scanner;
//ask the user what mode to enter, currently this only allows from competition mode but other modes such as programing/updating are in the works
public class ModeSelect {
    public void ModeSelectUI() {
        CompetitionMode competitionMode = new CompetitionMode();
        ConfigurationMode configurationMode = new ConfigurationMode();
        MusicMode musicMode = new MusicMode();
        ToolsManager toolsManager = new ToolsManager();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.print("Please select your mode: ");
            userInput = scanner.next().trim().toUpperCase(Locale.ROOT);

        } while (!userInput.matches("0") && !userInput.matches("1") && !userInput.matches("2") && !userInput.matches("3") && !userInput.matches("4") && !userInput.matches("EXIT") && !userInput.matches("CONFIG") && !userInput.matches("TOOLS") && !userInput.matches("COMPETITION") && !userInput.matches("MUSIC")&& !userInput.matches("\\?") && !userInput.matches("HELP")); {
            switch (userInput) {
                //quit on input of 0
                case "0" :
                    Quit();
                    break;

                //config mode
                case "1" :
                    configurationMode.ConfigModeInit();
                    break;

                //tools mode
                case "2" :
                    toolsManager.ToolsManagerInit();
                    break;

                //Competition mode
                case "3" : competitionMode.CompetitionModeMain();
                    break;

                    //music mode
                case "4" :
                    musicMode.MusicModeInit();
                    break;

                    //exit
                case "EXIT":
                    Quit();
                    break;
                    //config mode
                case "CONFIG":
                        configurationMode.ConfigModeInit();
                        break;
                     //tools mode
                case "TOOLS":
                    toolsManager.ToolsManagerInit();
                    break;
                    //competition mode
                case "COMPETITION":
                    competitionMode.CompetitionModeMain();
                    break;

                    //music mode
                case "MUSIC":
                    musicMode.MusicModeInit();
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
        System.out.println("1= Config Mode");
        System.out.println("2= tools manager");
        System.out.println("3= CompetitionMode");
        System.out.println("4= MUSIC MODE");
        System.out.println("EXIT = quit");
        System.out.println("CONFIG= Config Mode");
        System.out.println("TOOLS= tools manager");
        System.out.println("COMPETITION= CompetitionMode");
        System.out.println("MUSIC= MUSIC MODE");
        System.out.println("?, /?, HELP, help = help");
        ModeSelectUI();
    }
}
