package com.FrcPackageManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.util.Scanner;

public class MusicMode {
    public void MusicModeInit() {
        String musicToBePlayed = MusicSelector();
        String path = StringToPath(musicToBePlayed);
        PlayMusic(path);
    }
    public String MusicSelector() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "0";
        //ask what music to play
        System.out.println("What music should we play?");
        System.out.print("Music number: ");
        userInput = scanner.next().trim();
        return userInput;
    }
    public String StringToPath(String music) {
        ModeSelect modeSelect = new ModeSelect();
        switch (music) {
            case "0" :
                modeSelect.ModeSelectUI();
                break;
            case "1" :
                return "C:\\air_raid.wav";
            case "2" :
                return "string1";


        }
        return "0";
    }
    public void PlayMusic(String path) {
        File file = new File(path);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
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
}
