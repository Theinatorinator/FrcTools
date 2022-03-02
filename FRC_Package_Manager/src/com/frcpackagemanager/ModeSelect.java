package com.frcpackagemanager;

import java.util.Locale;
import java.util.Scanner;
//ask the user what mode to enter, currently this only allows from competition mode but other modes such as programing/updating are in the works
public class ModeSelect {
    public int ModeSelectUI() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.print("Would you like to enter competition mode (y/n):");
            userInput = scanner.next().trim().toUpperCase(Locale.ROOT);

        } while (!userInput.matches("Y") && !userInput.matches("N")); {
            if (userInput.matches("Y")) {
                return 1;
            } else if (userInput.matches("N")){
                System.out.println("Currently does not support other modes, closing");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.exit(2);
            }
        }

        return 0;
    }
}
