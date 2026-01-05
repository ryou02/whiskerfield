package main;

import java.net.URL;

import javax.sound.sampled.Clip;

public class Sound {
  Clip clip;
  URL soundURL[] = new URL[30]; 

  public Sound() {
    soundURL[0] = getClass().getResource("/res/sound/main.wav");
    
  }

  public void setFile(int i){
    try {
      javax.sound.sampled.AudioInputStream ais = javax.sound.sampled.AudioSystem.getAudioInputStream(soundURL[i]);
      clip = javax.sound.sampled.AudioSystem.getClip();
      clip.open(ais);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void play(){
    clip.start();
  }

  public void loop(){
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public void stop(){
    clip.stop();
  }
}
