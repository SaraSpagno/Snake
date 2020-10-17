import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.ImageIcon;





public class GamePlay extends JPanel implements KeyListener, ActionListener{

    private int[] snakeXlenght = new int[750];
    private int[] snakeYlenght = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;


    private ImageIcon rightMouth;
    private ImageIcon upMouth;
    private ImageIcon leftMouth;
    private ImageIcon downMouth;

    private int lenghtOfSnake = 3;
    private int moves = 0;


    private Timer timer;
    private int delay = 100;

    private ImageIcon snakeImage;
    private ImageIcon titleImage;


    private int [] enemyXpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int [] enemyYpos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

    private ImageIcon enemyImage;
    private Random random = new Random();

    private int xpos = random.nextInt(33);
    private int ypos = random.nextInt(23);

    private int score = 0;

    private int highScore = 0;


    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        if (moves==0) {
            snakeXlenght[2] = 50;
            snakeXlenght[1] = 75;
            snakeXlenght[0] = 100;

            snakeYlenght[2] = 100;
            snakeYlenght[1] = 100;
            snakeYlenght[0] = 100;
        }

        // draw Backgroud
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0, 905, 905);

        // draw title image border
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);


        // draw title image
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);


        // draw the border
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 576);


        // draw background for the game play
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        // draw scores
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores: "+score,780, 30);

        // draw lenght of snake
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length: "+lenghtOfSnake,780, 50);

        // draw HighScore
        g.setColor(Color.red);
        g.setFont(new Font("arial", Font.BOLD, 14));
        g.drawString("HighScore: "+highScore,30, 30);


        rightMouth = new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this, g, snakeXlenght[0], snakeYlenght[0]);


        for (int i=0; i<lenghtOfSnake; i++) {
            if (i==0 && right) {
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this, g, snakeXlenght[i], snakeYlenght[i]);
            }
            if (i==0 && left) {
                leftMouth = new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(this, g, snakeXlenght[i], snakeYlenght[i]);
            }
            if (i==0 && down) {
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(this, g, snakeXlenght[i], snakeYlenght[i]);
            }
            if (i==0 && up) {
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(this, g, snakeXlenght[i], snakeYlenght[i]);
            }

            if (i != 0) {
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this, g, snakeXlenght[i], snakeYlenght[i]);
            }
        }

        enemyImage = new ImageIcon("enemy.png");

        if (enemyXpos[xpos] == snakeXlenght[0] && enemyYpos[ypos] == snakeYlenght[0]) {
            lenghtOfSnake++;
            score++;
            xpos = random.nextInt(33);
            ypos = random.nextInt(23);

        }

        enemyImage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);

        for (int b = 1; b<lenghtOfSnake; b++) {
            if (snakeXlenght[b] == snakeXlenght[0] && snakeYlenght[b] == snakeYlenght[0]) {
                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD,  50));
                g.drawString("Game Over", 300 , 300);

                g.setFont(new Font("arial", Font.BOLD,  20));
                g.drawString("Press SPACE to Restart", 320 , 340);
            }

        }
        g.dispose();
    }

    @Override  
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {
            for (int r = lenghtOfSnake-1; r>=0; r--) {
                snakeYlenght[r+1] = snakeYlenght[r];


            }
            for (int r = lenghtOfSnake; r>=0; r--) {
                if (r==0) {
                    snakeXlenght[r] = snakeXlenght[r]+25;
                }
                else {
                    snakeXlenght[r] = snakeXlenght[r-1];
                }
                if (snakeXlenght[r] > 850) {
                    snakeXlenght[r]  = 25;
                }
            }

            repaint();
        }
        if (left) {
            for (int r = lenghtOfSnake-1; r>=0; r--) {
                snakeYlenght[r+1] = snakeYlenght[r];


            }
            for (int r = lenghtOfSnake; r>=0; r--) {
                if (r==0) {
                    snakeXlenght[r] = snakeXlenght[r]-25;
                }
                else {
                    snakeXlenght[r] = snakeXlenght[r-1];
                }
                if (snakeXlenght[r] < 25) {
                    snakeXlenght[r]  = 850;
                }
            }

            repaint();

        }
        if (down) {
            for (int r = lenghtOfSnake-1; r>=0; r--) {
                snakeXlenght[r+1] = snakeXlenght[r];


            }
            for (int r = lenghtOfSnake; r>=0; r--) {
                if (r==0) {
                    snakeYlenght[r] = snakeYlenght[r]+25;
                }
                else {
                    snakeYlenght[r] = snakeYlenght[r-1];
                }
                if (snakeYlenght[r] > 625) {
                    snakeYlenght[r]  = 75;
                }
            }

            repaint();


        }
        if (up) {
            for (int r = lenghtOfSnake-1; r>=0; r--) {
                snakeXlenght[r+1] = snakeXlenght[r];


            }
            for (int r = lenghtOfSnake; r>=0; r--) {
                if (r==0) {
                    snakeYlenght[r] = snakeYlenght[r]-25;
                }
                else {
                    snakeYlenght[r] = snakeYlenght[r-1];
                }
                if (snakeYlenght[r] < 75) {
                    snakeYlenght[r]  = 625;
                }
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (score > highScore) {
                highScore = score;
            }
            moves = 0;
            score = 0;
            lenghtOfSnake = 3;
            repaint();

        }        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            right = true;
            if (!left) {
                right = true;
            }
            else {
                right = false;
                left = true;
            }
            up = false;
            down = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            left = true;
            if (!right) {
                left = true;
            }
            else {
                left = false;
                right = true;
            }
            up = false;
            down = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            up = true;
            if (!down) {
                up = true;
            }
            else {
                up = false;
                down = true;
            }
            right = false;
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            down = true;
            if (!up) {
                down = true;
            }
            else {
                down = false;
                up = true;
            }
            right = false;
            left = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
