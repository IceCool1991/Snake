/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alu26600537w
 */
public class Board extends JPanel implements ActionListener {

    private static int NUM_ROWS;
    private static int NUM_COLS;

    MyKeyAdapter keyAdapter;

    public ScoreBoard scoreBoard;

    private int deltaTime;

    private Food food;
    private SpecialFood specialFood;

    private Random rand;

    private Snake snake;
    private final Timer timer;

    private boolean gameOver;
    private boolean specialFoodIsVisible;

    private JFrame parentFrame;

    public void setParentFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public Board() {
        super();
        timer = new Timer(deltaTime, this);
        keyAdapter = new MyKeyAdapter();
        setBackground(Color.BLACK);
        NUM_COLS = 100;
        NUM_ROWS = 100;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public static int getNUM_ROWS() {
        return NUM_ROWS;
    }

    public static int getNUM_COLS() {
        return NUM_COLS;
    }

    public static void setNUM_ROWS(int NUM_ROWS) {
        Board.NUM_ROWS = NUM_ROWS;
    }

    public static void setNUM_COLS(int NUM_COLS) {
        Board.NUM_COLS = NUM_COLS;
    }

    public void initValues() {
        setFocusable(true);
        requestFocusInWindow();
        rand = new Random();
        deltaTime = 100;
        gameOver = false;
        specialFoodIsVisible = false;
        snake = new Snake(3);
        food = new Food(snake);
        specialFood = new SpecialFood(snake);
    }

    public void initGame() {
        scoreBoard.reset();
        removeKeyListener(keyAdapter);
        initValues();
        timer.setDelay(deltaTime);
        timer.start();
        addKeyListener(keyAdapter);
        repaint();
    }

    private int squareWidth() {
        return getWidth() / NUM_COLS;
    }

    private int squareHeight() {
        return getHeight() / NUM_ROWS;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBorder(g);
        if (snake != null) {
            snake.draw(g, squareWidth(), squareHeight());
        }
        if (food != null) {
            food.draw(g, squareWidth(), squareHeight());
        }
        if (specialFood != null && specialFoodIsVisible) {
            specialFood.draw(g, squareWidth(), squareHeight());
        }
    }

    private void collisions() {
        Node head = snake.listNodes.get(0);
        switch (snake.getDirection()) {
            case LEFT:
                if (head.col - 1 < 0) {
                    gameOver = true;
                }
                break;
            case RIGHT:
                if (head.col + 1 >= NUM_COLS) {
                    gameOver = true;
                }
                break;
            case UP:
                if (head.row - 1 < 0) {
                    gameOver = true;
                }
                break;
            case DOWN:
                if (head.row - 1 >= NUM_ROWS) {
                    gameOver = true;
                }
                break;
            default:
                break;
        }
        for (Node n : snake.listNodes) {
            if (n != head) {
                if (head.col == n.col && head.row == n.row) {
                    gameOver = true;
                }
            }
        }
        if (head.row == food.row && head.col == food.col) {
            snake.eatFood();
            scoreBoard.increment(1);
            int randomNumber = rand.nextInt(5);
            if (randomNumber == 0) {
                specialFoodIsVisible = rand.nextBoolean();
            }
            food = new Food(snake);
        }

        if (head.row == specialFood.row && head.col == specialFood.col && specialFoodIsVisible) {
            snake.eatFood();
            scoreBoard.increment(5);
            specialFoodIsVisible = false;
            specialFood = new SpecialFood(snake);
        }
    }

    private void checkGameOver() {
        if (gameOver) {
            timer.stop();
            scoreBoard.gameOver();
            RecordsDialog d = new RecordsDialog(parentFrame, true, scoreBoard.getScore());
            d.setVisible(true);
        }
    }

    public void viewRecords() {
        RecordsDialog d = new RecordsDialog(parentFrame, true, 0);
        d.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        collisions();
        checkGameOver();
        if (!gameOver) {
            if (scoreBoard.getScore() > 0 && scoreBoard.getScore() % 10 == 0) {
                if (deltaTime > 50) {
                    deltaTime -= 10;
                    timer.setDelay(deltaTime);
                }
            }
            snake.move();
            repaint();
            Toolkit.getDefaultToolkit().sync();
            snake.isTurning = false;
        }
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(0, 0, squareWidth() * NUM_COLS, squareHeight() * NUM_ROWS);
    }

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    if (snake.getDirection() != DirectionType.DOWN && !snake.isTurning) {
                        snake.setDirection(DirectionType.UP);
                        snake.isTurning = true;
                    }
                    break;

                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != DirectionType.RIGHT && !snake.isTurning) {
                        snake.setDirection(DirectionType.LEFT);
                        snake.isTurning = true;
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != DirectionType.LEFT && !snake.isTurning) {
                        snake.setDirection(DirectionType.RIGHT);
                        snake.isTurning = true;
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != DirectionType.UP && !snake.isTurning) {
                        snake.setDirection(DirectionType.DOWN);
                        snake.isTurning = true;
                    }
                    break;
                case KeyEvent.VK_P:
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                    break;
                case KeyEvent.VK_R:
                    if (!timer.isRunning()) {
                        initGame();
                    }
                    break;
            }
            repaint();
        }
    }
}
