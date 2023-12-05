package kkreiju.moosicplayer;

import java.io.*;
import javax.sound.sampled.*;
import java.util.*;

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
    private boolean initializedData = false;
    
    public String getSongTitle;
    public String getArtistName;
    public int index = 0;
    public ArrayList<String> songTitle = new ArrayList<String>();
    public ArrayList<String> artistName = new ArrayList<String>();
    
    public void InitializeData(){
        if(!initializedData){
            
           songTitle.add("Always");
           artistName.add("Daniel Caesar"); 
           
           songTitle.add("Come Inside of My Heart");
           artistName.add("IV of Spades");
           
           songTitle.add("Disenchanted");
           artistName.add("My Chemical Romance");
           
           songTitle.add("Enchanted");
           artistName.add("Taylor Swift");
           
           songTitle.add("Fallen");
           artistName.add("Lola Amour");
           
           songTitle.add("lowkey");
           artistName.add("NIKI"); 
           
           songTitle.add("Maybe Maybe");
           artistName.add("Lola Amour"); 
           
           songTitle.add("Runaway Baby");
           artistName.add("Bruno Mars"); 
           
           songTitle.add("Sanctuary");
           artistName.add("Joji"); 
           
           songTitle.add("She Will Be Loved");
           artistName.add("Maroon 5"); 
           
           initializedData = true;
        }
        getSongTitle = songTitle.get(index);
        getArtistName = songTitle.get(index);
    }

    public void Play(String getSongTitle, String getArtistname) {
        try {
            File musicfile = new File("src\\main\\java\\kkreiju\\moosicplayer\\songs\\" + getSongTitle + ".wav");
            AudioInputStream audiostream = AudioSystem.getAudioInputStream(musicfile);
            
            if (!hasStarted && clip == null) {
                hasStarted = true;
                clip = AudioSystem.getClip();
                clip.open(audiostream);
                changeTime = 0;
                clip.setMicrosecondPosition(changeTime);
                
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
                calculateTime = (Integer.parseInt("" + durationMinutes) * 60) + Integer.parseInt(durationSeconds);
            }

            if (!getIsPlaying()) {
                clip.setMicrosecondPosition(changeTime);
                clip.start();
                setIsPlaying(true);
            } else {
                clip.stop();
                setIsPlaying(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
