package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
  Clip clip;
  Clip dialogueClip; // Separate clip for dialogue music
  URL soundURL[] = new URL[30]; 

  public Sound() {
    soundURL[0] = getClass().getResource("/res/sound/main.wav");
    soundURL[1] = getClass().getResource("/res/sound/grass.wav");
    soundURL[2] = getClass().getResource("/res/sound/pickup.wav");
    soundURL[3] = getClass().getResource("/res/sound/tile.wav");
    soundURL[4] = getClass().getResource("/res/sound/dialogue.wav");
  }

  public void setFile(int i){
    try {
      AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
      clip = AudioSystem.getClip();
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
  
  // Dialogue music methods (separate clip so it doesn't interrupt main music)
  public void playDialogueMusic() {
    try {
      if (dialogueClip != null && dialogueClip.isRunning()) {
        return; // Already playing
      }
      AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[4]);
      dialogueClip = AudioSystem.getClip();
      dialogueClip.open(ais);
      dialogueClip.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void stopDialogueMusic() {
    if (dialogueClip != null) {
      dialogueClip.stop();
      dialogueClip.close();
      dialogueClip = null;
    }
  }
}
