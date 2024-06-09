import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
    Clip sss;

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

    public void play(){
       
            sss.setFramePosition(0);
            sss.start();
        
    }
    
}
