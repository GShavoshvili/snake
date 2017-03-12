package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel {

    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    JFrame frame = Engine.createFrame(this, WIDTH, HEIGHT);
   
    
    Game game;

    GUI(Game game) {
        this.game = game;
        
        
    }
    
    
    void addKeyboard(){
    addKeyListener (new Keyboard(game));
    
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        clearScreen(g2);
        game.snake.paintFruit(g2);
        game.snake.paintSnake(g2);
    }

    private void clearScreen(Graphics2D g2) {
        g2.setColor(Color.BLACK);
       g2.fillRect(0, 0, WIDTH, HEIGHT);

    }

}
