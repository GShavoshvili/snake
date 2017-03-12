
package snake;

import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Engine {
    
    
     static final JFrame createFrame (JPanel panel, int width, int height) {
        JFrame frame = new JFrame();
        Dimension size = new Dimension(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setFocusable(true);
        panel.setPreferredSize(size);
        
        frame.add(panel);
        
        frame.setResizable(false);
        frame.pack();
        
        frame.setLocationRelativeTo(null);
      
        frame.setVisible(true);
         
        return frame;
 }    
    
    
    static final Clip createClip (String name, Class cl){
    
    Clip clip=null;
         try (AudioInputStream stream = AudioSystem.getAudioInputStream(cl.getResource(name))){
             
             clip = AudioSystem.getClip();
             clip.open(stream);
             
         } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
             Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
         } 
            
         return clip;
          
            }

         static final void resetClip (Clip c) {
            c.setFramePosition(0);
            c.stop();}
         static final void resetClip (Clip c[]) {
         for (Clip x : c)
         {x.setFramePosition(0);
            x.stop();}
         
         }
         static final void restartClip (Clip c)
         {c.setFramePosition(0);
          c.start();}



}






    
    
    
    
    
    
    

