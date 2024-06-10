import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
    Clip sss; // audio pre-loaded data 
    // path or filename of the sound file is argument, create an audio clip object using the AudioSystem class
    public void setFile(String soundfileName)
    {
        try{
            File file = new File(soundfileName);
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            sss = AudioSystem.getClip();
            sss.open(stream);
            
        }
        catch(Exception e){

        }
    }
//play the sound
    public void play(){
       
            sss.setFramePosition(0);
            sss.start();
        
    }

    
    
}
