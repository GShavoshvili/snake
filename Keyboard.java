package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
/ The Keyboard class handles keyboard input (duh)
 */
public class Keyboard implements KeyListener {

    Game game;
    int key;

    Keyboard(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();

        // WASD to ARROW KEYS
        switch (key) {
            case KeyEvent.VK_A:
                key = 37;
                break;
            case KeyEvent.VK_W:
                key = 38;
                break;
            case KeyEvent.VK_D:
                key = 39;
                break;
            case KeyEvent.VK_S:
                key = 40;
                break;
        }
        System.out.println(key);
        if (key > 36 && key < 41 && (key != 37 + game.snake.dir || game.gameState == Game.State.CANSTART)) { // if is one of the directional controls AND (NOT same direction OR ) 

            if (game.gameState == Game.State.RUNNING && game.pressed) {
                game.snake.lastDir = game.snake.dir;
                System.out.println("call");
                game.call = true;
            }

            if (game.gameState == Game.State.CANSTART) {
                game.gameState = Game.State.RUNNING;
            }
            if (((key - 35 != game.snake.dir && key - 39 != game.snake.dir && !game.afterCall) && (game.gameState == Game.State.CANSTART || game.gameState == Game.State.RUNNING)) || game.snake.score == 0 && (game.gameState == Game.State.CANSTART || game.gameState == Game.State.RUNNING)) {
                game.snake.dir = key - 37;
                game.pressed = true;
            }

        } else if (key == 10) {
            if (game.gameState == Game.State.LOST) {

                game.initGame();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
