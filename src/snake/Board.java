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
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alu26600537w
 */
public class Board extends JPanel implements ActionListener {

    public static final int NUM_ROWS = 40;
    public static final int NUM_COLS = 80;

    MyKeyAdapter keyAdapter;

    public ScoreBoard scoreBoard;

    private int deltaTime;

    private Food food;

    private Snake snake;
    private final Timer timer;

    private boolean gameOver;

    public Board() {
        super();
        timer = new Timer(deltaTime, this);
        keyAdapter = new MyKeyAdapter();
        setBackground(Color.BLACK);
    }
    
    public void setScoreBoard(ScoreBoard scoreBoard){
        this.scoreBoard = scoreBoard;
    }

    public void initValues() {
        setFocusable(true);
        requestFocusInWindow();
        deltaTime = 100;
        gameOver = false;
        snake = new Snake(3);
        food = new Food(snake);
    }

    public void initGame() {
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
        if (snake != null) {
            snake.draw(g, squareWidth(), squareHeight());
        }
        if (food != null){
            food.draw(g, squareWidth(), squareHeight());
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
        if(head.row == food.row && head.col == food.col){
            snake.eatFood();
            scoreBoard.increment(1);
            food = new Food(snake);
        }
    }

    private void checkGameOver() {
        if (gameOver) {
            timer.stop();
            scoreBoard.gameOver();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        collisions();
        checkGameOver();
        if (!gameOver) {
            snake.move();
            repaint();
            Toolkit.getDefaultToolkit().sync();
        }
    }

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    if (snake.getDirection() != DirectionType.DOWN) {
                        snake.setDirection(DirectionType.UP);
                    }
                    break;

                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != DirectionType.RIGHT) {
                        snake.setDirection(DirectionType.LEFT);
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != DirectionType.LEFT) {
                        snake.setDirection(DirectionType.RIGHT);
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != DirectionType.UP) {
                        snake.setDirection(DirectionType.DOWN);
                    }
                    break;
            }
            repaint();
        }
    }
}
