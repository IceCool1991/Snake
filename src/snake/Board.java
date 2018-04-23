/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

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
public class Board extends JPanel implements ActionListener{
    
    MyKeyAdapter keyAdapter;
    
    public ScoreBoard scoreBoard;
    
    private int deltaTime;
    
    private Food food;
    private SpecialFood specialFood;
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
    }
    
    public void initGame() {
        removeKeyListener(keyAdapter);
        initValues();
        scoreBoard.reset();
        timer.setDelay(deltaTime);
        timer.start();
        addKeyListener(keyAdapter);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:

                    break;
                case KeyEvent.VK_RIGHT:

                    break;
                case KeyEvent.VK_UP:

                    break;
                case KeyEvent.VK_DOWN:

                    break;
                case KeyEvent.VK_P:

                    break;

                default:
                    break;
            }
        }
    }
    
}
