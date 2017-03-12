
package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Keyboard implements KeyListener {
   Game game;
   int key;
 
   Keyboard (Game game) {
   this.game = game;
   }
   
   
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                key = e.getKeyCode();
                System.out.println(key);
                if (key > 36 && key < 41 && (key != 37 + game.snake.dir || game.gameState == 1)) {

                    if (game.gameState == 2 && game.pressed) {
                        game.snake.lastDir = game.snake.dir;
                        System.out.println("call");
                        game.call = true;
                    }

                    if (game.gameState == 1) {
                        game.gameState = 2;
                    }
                    if (((key - 35 != game.snake.dir && key - 39 != game.snake.dir) && (game.gameState == 1 || game.gameState == 2) && ((game.afterCall) ? (key - 35 != game.snake.lastDir && key - 39 != game.snake.lastDir) : true)) || game.snake.score == 0 && (game.gameState == 1 || game.gameState == 2)) {
                        game.snake.dir = key - 37;
                        game.pressed = true;
                    }

                } else if (key == 10) {
                    if (game.gameState == 3) {

                        game.initGame();
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        
    
}
