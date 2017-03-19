package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;


/*
/ The GUI class is responsible for the JPanel
/ It holds an instance of Keyboard (KeyListener)
/ and calls render methods located in other classes
 */
public class GUI extends JPanel {

    static final int WIDTH = 600;
    static final int FOR_SCORE = 50;
    static final int HEIGHT = WIDTH + FOR_SCORE;
    JFrame frame = Engine.createFrame(this, WIDTH, HEIGHT);

    Game game;

    GUI(Game game) {
        this.game = game;

    }

    void addKeyboard() {
        addKeyListener(new Keyboard(game));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        clearScreen(g2);
        
        
        g2.setColor(Color.WHITE);
        g2.fillRect(0,FOR_SCORE-2,WIDTH,2);
        Font font = new Font("Dialog", Font.PLAIN,20);
        g2.setFont(font);
        g2.drawString("Score: "+String.valueOf(game.snake.score), 30, 30);
        
        
        
        game.snake.paintFruit(g2);
        game.snake.paintSnake(g2);
    }

    private void clearScreen(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, WIDTH, HEIGHT);
        

    }

}
