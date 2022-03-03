package com.FrcPackageManager;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

public class MusicMode {
    private final String propFileLocation = "out/production/FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/Config.properties";
    private final Properties prop = new Properties();
    private final Scanner scanner = new Scanner(System.in);
    private float storeVolume = 1;
    private Clip clip;
    public void MusicModeInit() {
        try {
            prop.load(new FileInputStream(propFileLocation));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
        MusicModeMain();
    }
    public void MusicModeMain() {
        String musicToBePlayed = MusicSelector();
        String path = StringToPath(musicToBePlayed);
        PlayMusic(path);
    }
    public String MusicSelector() {
        String userInput = "0";
        //ask what music to play
        do {
            System.out.println("What music should we play?");
            System.out.print("Music number: ");
            userInput = scanner.next().trim().toUpperCase(Locale.ROOT);
        } while (!userInput.matches("0") && !userInput.matches("1") && !userInput.matches("2") && !userInput.matches("3") && !userInput.matches("4") && !userInput.matches("\\?") && !userInput.matches("HELP") && !userInput.matches("STOP")&& !userInput.matches("VOLUME")); {
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
                return prop.getProperty("song1");
            case "2" :
                return prop.getProperty("song2");
            case "3" :
                return  prop.getProperty("song3");
            case  "4" :
                return  prop.getProperty("song4");
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
            case "VOLUME":
                VolumeControl();
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
            SetVolume(storeVolume);
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
        System.out.println("3 for GIGA CHAD MUSIC");
        System.out.println("4 for modern desert music");
        System.out.println("stop to stop");
        System.out.println("/?, ? or HELP for help");
        MusicModeMain();

    }

    public void VolumeControl() {
        float userInput = 0;
        float currentVolume = 0;
        currentVolume = GetVolume();
        System.out.println("The current volume is " + currentVolume);
        System.out.print("Set new volume:  ");
        userInput = scanner.nextFloat();
        SetVolume(userInput);
        storeVolume = userInput;
        MusicModeMain();
    }

    public float GetVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void SetVolume(float volume) {
        if (volume < 0f || volume > 2f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}
