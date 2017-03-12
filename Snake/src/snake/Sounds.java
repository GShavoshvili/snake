package snake;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;

public class Sounds {

    Clip eat;
    Clip snake1;
    Clip snake2;
    Clip snake3;
    Clip snake4;
    Clip snake5;
    Clip snake6;
    Clip snake7;
    Clip snake8;
    Clip lostSounds[] = new Clip[8];

    Sounds(Class game) {
        
        eat = Engine.createClip("Eat.wav", game);
        snake1 = Engine.createClip("snake1.wav", game);
        snake2 = Engine.createClip("snake2.wav", game);
        snake3 = Engine.createClip("snake3.wav", game);
        snake4 = Engine.createClip("snake4.wav", game);
        snake5 = Engine.createClip("snake5.wav", game);
        snake6 = Engine.createClip("snake6.wav", game);
        snake7 = Engine.createClip("snake7.wav", game);
        snake8 = Engine.createClip("snake8.wav", game);

        lostSounds[0] = snake1;
        lostSounds[1] = snake2;
        lostSounds[2] = snake3;
        lostSounds[3] = snake4;
        lostSounds[4] = snake5;
        lostSounds[5] = snake6;
        lostSounds[6] = snake7;
        lostSounds[7] = snake8;

    }
    
    void resetLost() {
     Engine.resetClip(lostSounds);
    
    
    }
    void restartEat()
    {Engine.restartClip(eat);}

}




