/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Graphics;
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

    public static final int NUM_ROWS = 30;
    public static final int NUM_COLS = 30;

    MyKeyAdapter keyAdapter;

    public ScoreBoard scoreBoard;

    private int deltaTime;
    private DirectionType direction;

    private Food food;
    private int foodX;
    private int foodY;

    private Snake snake;
    private Timer timer;

    private boolean gameOver;

    public Board() {
        super();
        timer = new Timer(deltaTime, this);
        keyAdapter = new MyKeyAdapter();
    }

    public void initValues() {
        setFocusable(true);
        deltaTime = 500;
        gameOver = false;
        snake = new Snake(3);
        
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
        if(snake!=null){
        snake.draw(g,squareWidth(),squareHeight());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:
                    if (direction != DirectionType.DOWN) {
                        direction = DirectionType.UP;
                    }
                    break;

                case KeyEvent.VK_LEFT:
                    if (direction != DirectionType.RIGHT) {
                        direction = DirectionType.LEFT;
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != DirectionType.LEFT) {
                        direction = DirectionType.RIGHT;
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != DirectionType.UP) {
                        direction = DirectionType.DOWN;
                    }
                    break;
            }
            repaint();
        }
    }

}
