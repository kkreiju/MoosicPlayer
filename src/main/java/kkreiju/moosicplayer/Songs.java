package kkreiju.moosicplayer;

import java.io.*;
import javax.sound.sampled.*;

/**
 *
 * @author Arjay
 */
public class Songs{

    public String displayEndTime;
    public int calculateTime;
    public long changeTime;
    private boolean isPlaying = false;
    public boolean hasStarted = false;
    Clip clip = null;

    public void Play() {
        try {
            File musicfile = new File("src\\main\\java\\kkreiju\\moosicplayer\\songs\\ciomh.wav");
            AudioInputStream audiostream = AudioSystem.getAudioInputStream(musicfile);
            
            if (!hasStarted && clip == null) {
                hasStarted = true;
                clip = AudioSystem.getClip();
                clip.open(audiostream);
                //get minutes of the song
                AudioFileFormat format = AudioSystem.getAudioFileFormat(musicfile);
                long durationMicroseconds = (long) (format.getFrameLength() * 1_000_000.0 / format.getFormat().getFrameRate());
                long durationMinutes = durationMicroseconds / 60_000_000;
                long remainingSeconds = (durationMicroseconds % 60_000_000) / 1_000_000;
                String durationSeconds = "" + remainingSeconds;
                if (durationSeconds.length() == 1) {
                    durationSeconds = "0".concat(durationSeconds);
                }
                displayEndTime = durationMinutes + ":" + durationSeconds;
                calculateTime = (Integer.parseInt("" + durationMinutes) * 60) + Integer.parseInt(durationSeconds) ;

                System.out.println("Duration: " + durationMinutes + " minutes and " + durationSeconds + " seconds");
            }

            if (!getIsPlaying()) {
                clip.start();
                System.out.println("Play");
                setIsPlaying(true);
            } else {
                clip.stop();
                System.out.println("Pause");
                setIsPlaying(false);
            }
        } catch (Exception e) {

        }

    }
    
    public void ChangeValue(){
        if(!clip.isRunning()){
            clip.setFramePosition(0);
            clip.start();
        }
        clip.setMicrosecondPosition(changeTime);
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean b) {
        this.isPlaying = b;
    }
    
    public int getCalculateTime(){
        return calculateTime;
    }
}
