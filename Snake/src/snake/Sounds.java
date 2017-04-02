package snake;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;


/*
/ The Sound class handles sounds 
 */
public class Sounds {

    Clip eat;
    Clip wallHit;
    Clip bite;
  
    boolean muteSFX;
    boolean muteMusic;
    Sounds(Class game) {

        eat = Engine.createClip("Eat.wav", game);
        wallHit = Engine.createClip("wallHit4.wav", game);
        bite  = Engine.createClip("bite.wav", game);
       
     

    }

   

    void restartEat() {
        Engine.restartClip(eat);
    }
    void restartWallHit() {
        Engine.restartClip(wallHit);
    }
    void restartBite(){
        Engine.restartClip(bite);
    }
}
