package snake;

import java.util.Random;
import javax.swing.JFrame;

/*
/ The Game class holds all others
/ It is responsible for game state (main loop, fps count, FRUIT generation (this one might be moved to Snake) )
 */
public class Game {
// SNAKE writing in menu with snake blocks 

    JFrame frame;
    Snake snake;
    GUI graphics;
    Sounds sounds;
    Random rand = new Random(); //move
    Random loseRand = new Random();//move SOUND

    volatile boolean pressed;
    volatile boolean call;
    volatile boolean afterCall;

    enum State {
        MENU, CANSTART, RUNNING, LOST, WON, LEADERBOARD
    }
    volatile State gameState; // menu 0 canStart 1 running 2 lost 3 won 4 leaderboard 5

    double lastTime;  // Timer
    double timeNow;
    double dtime;
    final double step = 1000 / 12;

    public Game() {
        snake = new Snake(this);
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

        

        graphics.frame.setTitle("Snake v0.5" + "    " + "Score:" + snake.score + "    Made by Giorgi Shavoshvili");
        gameState = State.CANSTART;
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
                graphics.frame.setTitle("Snake v0.5" + "    " + "Score:" + snake.score + "FPS:" + String.valueOf(ups) + "Made by Giorgi Shavoshvili");
                ups = 0;
                dups = 0;
            }
            if (gameState == State.RUNNING) {
                dtime += timeNow - lastTime;

                if (dtime >= step || call || (afterCall && dtime >= step / 2)) {

                    update();
                    render();
                    ups++;
                    if (call) {

                        call = false;
                        dtime = 0;
                        afterCall = true;
                    } else if (afterCall) {

                        dtime = 0;
                        afterCall = false;
                    } else {
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
                snake.body.set(0, snake.body.get(0) - 1);

                break;
            case 1:
                snake.body.set(0, snake.body.get(0) - 30);

                break;
            case 2:
                snake.body.set(0, snake.body.get(0) + 1);

                break;
            case 3:
                snake.body.set(0, snake.body.get(0) + 30);

                break;
        }

        if (snake.body.get(0) == snake.fruitPos) {

            sounds.restartEat();

            snake.addTail += snake.FRUIT_VALUE;
            snake.score += snake.SCORE_UP;
            graphics.frame.setTitle("Snake v0.5" + "    " + "Score:" + snake.score + "    Made by Giorgi Shavoshvili");
            setFruit();
        } else if (snake.checkLose()>0) {
             if (snake.checkLose() == 2){
            sounds.restartBite();}
            else {sounds.restartWallHit();}
            snake.body.set(0, snake.lastTail.get(0));
            System.out.println("lost");
           
            
            gameState = State.LOST;
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
