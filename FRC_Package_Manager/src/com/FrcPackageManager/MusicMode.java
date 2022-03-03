package com.FrcPackageManager;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class MusicMode {
    String propFileLocation = "out/production/FRC_Package_Manager/out/production/FRC_Package_Manager/com/frcpackagemanager/Config.properties";
    Properties prop = new Properties();
    Scanner scanner = new Scanner(System.in);
    float storeVolume = 1;
    Clip clip;
    boolean radioMode = false;
    Random random = new Random();
    int randomNumber = 1;
    int minSong = 1;
    int maxSong = 5;

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
        System.out.println("Welcome to Music Mode, please enter a Music Mode command");
        String musicToBePlayed = MusicSelector();
        String path = StringToPath(musicToBePlayed);
        PlayMusic(path);
    }

    public String MusicSelector() {
        String userInput = "0";
        //ask what music to play
        do {
            System.out.print("Music Command: ");
            userInput = scanner.next().trim().toUpperCase(Locale.ROOT);
        } while (!userInput.matches("0") && !userInput.matches("1") && !userInput.matches("2") && !userInput.matches("3") && !userInput.matches("4") && !userInput.matches("5") && !userInput.matches("6") && !userInput.matches("7") && !userInput.matches("8") && !userInput.matches("9") && !userInput.matches("10") && !userInput.matches("LOOP") && !userInput.matches("\\?") && !userInput.matches("HELP") && !userInput.matches("STOP") && !userInput.matches("VOLUME") && !userInput.matches("RADIO"));
        {
            return userInput;
        }
    }

    public String StringToPath(String music) {

        ModeSelect modeSelect = new ModeSelect();
        switch (music) {
            case "0":
                modeSelect.ModeSelectUI();
                break;
            case "1":
                System.out.println("now playing " + prop.getProperty("music1Name"));
                return prop.getProperty("song1");
            case "2":
                System.out.println("now playing " + prop.getProperty("music2Name"));
                return prop.getProperty("song2");
            case "3":
                System.out.println("now playing " + prop.getProperty("music3Name"));
                return prop.getProperty("song3");
            case "4":
                System.out.println("now playing " + prop.getProperty("music4Name"));
                return prop.getProperty("song4");
            case "5":
                System.out.println("now playing " + prop.getProperty("music5Name"));
                return prop.getProperty("song5");
            case "6":
                System.out.println("now playing " + prop.getProperty("music6Name"));
                return prop.getProperty("song6");
            case "7":
                System.out.println("now playing " + prop.getProperty("music7Name"));
                return prop.getProperty("song7");
            case "8":
                System.out.println("now playing " + prop.getProperty("music8Name"));
                return prop.getProperty("song8");
            case "9":
                System.out.println("now playing " + prop.getProperty("music9Name"));
                return prop.getProperty("song9");
            case "10":
                System.out.println("now playing " + prop.getProperty("music10Name"));
                return prop.getProperty("song10");
            case "?":
                Help();
                break;
            case "/?":
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
            case "LOOP":
                loopMode();
                break;
            case "RADIO":
                radioMode = true;
                System.out.println("Now starting Radio");
                try {
                    RadioMode();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "1";


        }

        return "1";
    }

    public void PlayMusic(String path) {
        File file = new File(path);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            SetVolume(storeVolume);
            MusicModeMain();
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

    public void PlayMusicRadio(String path) {
        File file = new File(path);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.stop();
            clip.start();
            SetVolume(storeVolume);
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
        MusicModeMain();
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

    public void loopMode() {
        int times = 5;
        int userInput = 0;
        System.out.print("how many times to loop: ");
        userInput = scanner.nextInt();
        times = userInput;
        clip.loop(times);
        MusicModeMain();

    }

    public void RadioMode() throws InterruptedException {
        boolean threadLaunched = false;
        PlayMusicRadio(prop.getProperty("song1"));
        do {
            int length = clip.getFrameLength();
            if (clip.getFramePosition() == length) {
                if (threadLaunched == false){
                    new Thread(radioStopThread).start();
                    threadLaunched = true;
                }
                randomNumber = random.nextInt(minSong, maxSong);
                String number = String.valueOf(randomNumber);
                String path = StringToPath(number);
                PlayMusicRadio(path);
                randomNumber++;

            }


        } while (radioMode == true);
        radioMode = false;
        clip.stop();
        MusicModeMain();
    }

    Runnable radioStopThread = new Runnable() {
        @Override
        public void run() {
            String userInput = "";
            do {
                userInput = scanner.next().trim().toUpperCase(Locale.ROOT);
            } while (!userInput.matches("STOP")); {
                   radioMode = false;
                   clip.stop();
                }
            }

    };





}
