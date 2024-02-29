package src;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;


public class Sound {
    // URL sound1 = Sound.class.getResource("1.wav");
    public static void play(URL url){
        try {
            File sound = new File(url.getPath());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            //System.out.println(url.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}