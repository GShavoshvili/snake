package snake;

import java.awt.event.KeyListener;
import java.util.Random;
import javax.sound.sampled.Clip;

import javax.swing.JFrame;

public class Game {
// IDEAS 1: snake snaake snaaaaaaake 2: while(true) if (running) cheta skachet 3: 2 raza render ne vizivai, poprobui sdelat 180* razvorot, SNAKE writing in menu with snake blocks *** shit turnz, delay too low

    JFrame frame;
    Snake snake;
    GUI graphics;
    Sounds sounds;
    Random rand = new Random(); //move
    Random loseRand = new Random();//move SOUND
 
    

    volatile boolean pressed;
    volatile boolean call;
    volatile boolean afterCall;
    volatile int gameState; // menu 0 canStart 1 running 2 lost 3 won 4 leaderboard 5

    double lastTime;  // Timer
    double timeNow;
    double dtime;
    final double step = 1000 / 12;

  
   

    public Game() {
        snake = new Snake();
        graphics = new GUI(this);
        sounds = new Sounds(Game.class);
        
        
        
       
    }

    void initGame() {
        lastTime = System.currentTimeMillis();
        timeNow = 0;
        dtime = 0;

        pressed = false;
        call = false;

        setFruit();
        snake.initSnake();
        
        sounds.resetLost();
        
        graphics.frame.setTitle("Snake v0.5" + "    " + "Score:" + snake.score + "    Made by Giorgi Shavoshvili");
        gameState = 1;
        graphics.frame.repaint();
        
    }

    void setFruit() {

        int num = rand.nextInt(900);
        if (!snake.body.contains(num)) {
            snake.fruitPos = num;
        } else {
            setFruit();
        }

    }

    

    
            

    

    synchronized void run() {
     
        graphics.addKeyboard();
        int ups = 0;
        int dups = 0;
        while (true) {
            timeNow = System.currentTimeMillis();
            dups += timeNow - lastTime;
            if (dups >= 1000) {
                ups = 0;
                dups = 0;
            }
            if (gameState == 2) {
                dtime += timeNow - lastTime;

                if (dtime >= step || call) {

                    update();
                    render();
                    ups++;
                    if (call) {
                        dtime = 0;
                        call = false;
                        afterCall = true;
                    } else {
                        if (afterCall) {
                            afterCall = false;
                        }
                        dtime -= step;
                    }

                }

            }
            lastTime = timeNow;

        }

    }

    void update() {
        
            snake.lastTail.clear();
            snake.lastTail.addAll(snake.body);

        
     
        switch (call ? snake.lastDir : snake.dir) {
            case 0:
                snake.body.set(0, snake.body.get(0)-1);
              

                break;
            case 1:
                snake.body.set(0, snake.body.get(0)-30);
               

                break;
            case 2:
                snake.body.set(0, snake.body.get(0)+1);
               

                break;
            case 3:
                snake.body.set(0, snake.body.get(0)+30);
                

                break;
        }

        if (snake.body.get(0) == snake.fruitPos) {
            System.out.println(sounds.eat.getFramePosition());
            sounds.restartEat();

            snake.addTail += snake.FRUIT_VALUE;
            snake.score += snake.SCORE_UP;
            graphics.frame.setTitle("Snake v0.5" + "    " + "Score:" + snake.score + "    Made by Giorgi Shavoshvili");
            setFruit();
        } else if (snake.checkLose()) {
            
            snake.body.set(0, snake.lastTail.get(0));
            System.out.println("lost");
            sounds.lostSounds[loseRand.nextInt(8)].start();
            gameState = 3;
            if (call) {
                snake.dir = snake.lastDir;
            }
            return;
        }

        // tut

        if (snake.addTail > 0) {
            snake.body.add(1, snake.lastTail.get(0));

            snake.addTail--;

        } else if (snake.body.size() > 0) {
            

            for (int i = snake.body.size() - 1; i > 0; i--) {
                snake.body.set(i, snake.lastTail.get(i - 1));

            }
        }
        pressed = false;

    }

    void render() {

        graphics.frame.repaint();
    }

    

    public static void main(String[] args) {

        Game game = new Game();
        
       
        game.initGame();
        game.run();

    }

}
