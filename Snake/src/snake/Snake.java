package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


/*
/ The Snake class is responsible for the core gameplay
/ It holds methods and parameters
/ for the snake, fruit score etc.
 */
public class Snake {

    Game game;
    
    final int INITIAL_POSITION = 455; //0-899
    final int CELL_WIDTH = 18;
    final int CELL_HEIGHT = 18;
    final int CELL_OFFSET = 1;
    final int SCORE_UP = 100;
    final int FRUIT_VALUE = 5;
    int fruitPos;
    volatile int dir; // left up right down
    volatile int lastDir;

    int x;
    int y;
    int tailX;
    int tailY;
    int tailPiece;
    int prevTailPiece;

    volatile int score;

    int addTail;

    Snake(Game game) {
        this.game = game;
        initSnake();
        
    }

    final void initSnake() {
        body.clear();
        lastTail.clear();
        dir = 0; // left up right down
        body.add(INITIAL_POSITION); // 0 - 899

        addTail = 0;
        score = 0;

        lastDir = 0;

    }
    List<Integer> body = new ArrayList<>();
    List<Integer> lastTail = new ArrayList<>();

    int checkLose() { // wall - 1, eat - 2, no - 0
       if ((lastTail.get(0) % 30 == 0 && lastTail.get(0) == body.get(0) + 1)
                || (lastTail.get(0) % 30 == 29 && lastTail.get(0) == body.get(0) - 1)
                || (lastTail.get(0) / 30 == 0 && lastTail.get(0) == body.get(0) + 30)
                || (lastTail.get(0) / 30 == 29 && lastTail.get(0) == body.get(0) - 30)){return 1;}
                else if (body.lastIndexOf(body.get(0)) > 0)
                        {System.out.println("eat");return 2;}
                return 0;

    }

    void paintFruit(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        if (fruitPos >= 0) {
            g2.fillOval((fruitPos % 30) * 20 + 3, (fruitPos / 30) * 20 + 3 + GUI.FOR_SCORE , 14, 14);
        }

    }

    void paintSnake(Graphics2D g2) {

        System.out.println(body.get(0));
        /*snake
         g2.setColor(Color.WHITE);
        x = (body.get(0) % 30) * 20;
        y = (body.get(0) / 30) * 20;
        g2.fillRect(x + 1, y + 1, 18, 18);*/
        // arrow

        for (int i = 0; i < body.size(); i++) {
            //body
            g2.setColor(Color.WHITE);
            tailX = body.get(i) % 30 * 20;
            tailY = body.get(i) / 30 * 20 + GUI.FOR_SCORE;

            g2.fillRect(tailX + CELL_OFFSET, tailY + CELL_OFFSET , CELL_WIDTH, CELL_HEIGHT);

        }
        //arrow
        x = body.get(0) % 30 * 20;
        y = body.get(0) / 30 * 20 + GUI.FOR_SCORE;
        g2.setColor(Color.BLACK);

        switch (dir) {
            case 0:
                g2.fillPolygon(new int[]{x + 6, x + 2, x + 6}, new int[]{y + 6, y + 10, y + 14}, 3);
                break;
            case 1:
                g2.fillPolygon(new int[]{x + 6, x + 10, x + 14}, new int[]{y + 6, y + 2, y + 6}, 3);
                break;
            case 2:
                g2.fillPolygon(new int[]{x + 14, x + 18, x + 14}, new int[]{y + 6, y + 10, y + 14}, 3);
                break;
            case 3:
                g2.fillPolygon(new int[]{x + 6, x + 10, x + 14}, new int[]{y + 14, y + 18, y + 14}, 3);
                break;
        }
        // tail

        /* lines
            g2.setColor(Color.BLACK);
            if (i > 0 && i < body.size() - 1) {
                if (body.get(i - 1) == body.get(i) - 1 || body.get(i + 1) == body.get(i) - 1) {
                    g2.fillRect(tailX + 4, tailY + 8, 8, 4);
                }
                if (body.get(i - 1) == body.get(i) + 1 || body.get(i + 1) == body.get(i) + 1) {
                    g2.fillRect(tailX + 8, tailY + 8, 8, 4);
                }
                if (body.get(i - 1) == body.get(i) - 30 || body.get(i + 1) == body.get(i) - 30) {
                    g2.fillRect(tailX + 8, tailY + 4, 4, 6);
                }
                if (body.get(i - 1) == body.get(i) + 30 || body.get(i + 1) == body.get(i) + 30) {
                    g2.fillRect(tailX + 8, tailY + 10, 4, 6);
                }

            } else if (i == 0 && body.size() > 1) {
                if (body.get(0) == body.get(i) - 1 || body.get(i + 1) == body.get(i) - 1) {
                    g2.fillRect(tailX + 4, tailY + 8, 8, 4);
                }
                if (body.get(0) == body.get(i) + 1 || body.get(i + 1) == body.get(i) + 1) {
                    g2.fillRect(tailX + 8, tailY + 8, 8, 4);
                }
                if (body.get(0) == body.get(i) - 30 || body.get(i + 1) == body.get(i) - 30) {
                    g2.fillRect(tailX + 8, tailY + 4, 4, 6);
                }
                if (body.get(0) == body.get(i) + 30 || body.get(i + 1) == body.get(i) + 30) {
                    g2.fillRect(tailX + 8, tailY + 10, 4, 6);
                }

            } else if (i == 0 && body.size() == 1) {
                if (body.get(0) == body.get(i) - 1) {
                    g2.fillRect(tailX + 4, tailY + 8, 6, 4);
                    g2.fillRect(tailX + 10, tailY + 8, 6, 4);
                }

                if (body.get(0) == body.get(i) - 30) {
                    g2.fillRect(tailX + 8, tailY + 4, 4, 6);
                    g2.fillRect(tailX + 8, tailY + 10, 4, 6);
                }
            } else {
                if (body.get(i - 1) == body.get(i) - 1 || body.get(i - 1) == body.get(i) + 1) {
                    g2.fillRect(tailX + 4, tailY + 8, 6, 4);
                    g2.fillRect(tailX + 10, tailY + 8, 6, 4);
                }

                if (body.get(i - 1) == body.get(i) - 30 || body.get(i - 1) == body.get(i) + 30) {
                    g2.fillRect(tailX + 8, tailY + 4, 4, 6);
                    g2.fillRect(tailX + 8, tailY + 10, 4, 6);
                } 

            }

        }*/
    }

}
