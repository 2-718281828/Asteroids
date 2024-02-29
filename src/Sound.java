package src;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;


public class Sound {
    public static void play(String name){
    String classPath;
    classPath = Sound.class.getResource("").getPath();
       try {
        File sound = new File(classPath+name);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}