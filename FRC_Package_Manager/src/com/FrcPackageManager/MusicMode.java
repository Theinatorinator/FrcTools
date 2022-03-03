package com.FrcPackageManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class MusicMode {
    private Clip clip;
    public void MusicModeInit() {
        String musicToBePlayed = MusicSelector();
        String path = StringToPath(musicToBePlayed);
        PlayMusic(path);
    }
    public String MusicSelector() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "0";
        //ask what music to play
        do {
            System.out.println("What music should we play?");
            System.out.print("Music number: ");
            userInput = scanner.next().trim().toUpperCase(Locale.ROOT);
        } while (!userInput.matches("0") && !userInput.matches("1") && !userInput.matches("2") && !userInput.matches("3") && !userInput.matches("4") && !userInput.matches("\\?") && !userInput.matches("HELP") && !userInput.matches("STOP")); {
            return userInput;
        }
    }
    public String StringToPath(String music) {
        ModeSelect modeSelect = new ModeSelect();
        switch (music) {
            case "0" :
                StopClip();
                modeSelect.ModeSelectUI();
                break;
            case "1" :
                return "out/production/FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/raid.wav";
            case "2" :
                return "out/production/FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/freshFriday.wav";
            case "3" :
                return  "";
            case  "4" :
                return "";
            case  "?" :
                Help();
                break;
            case "/?" :
                Help();
                break;
            case "HELP":
                Help();
                break;
            case "STOP":
                StopClip();
                break;



        }
        return "0";
    }
    public void PlayMusic(String path) {
        File file = new File(path);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            MusicModeInit();
        } catch (java.net.MalformedURLException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch (javax.sound.sampled.UnsupportedAudioFileException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
    public void StopClip() {
        clip.stop();
        MusicModeInit();
    }
    public void Help() {
        System.out.println("0 to EXIT");
        System.out.println("1 For AIR RAID SIREN");
        System.out.println("3 for ");
        System.out.println("4 for");
        System.out.printf("stop to stop");
        System.out.println("/?, ? or HELP for help");
        MusicModeInit();

    }
}
