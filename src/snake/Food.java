/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alu26600537w
 */
public class Food {

    protected int row, col;

    public Food(Snake snake) {
        // Set our food's x & y position to a random position
        boolean hit = true;
        while (hit) {
            hit = false;
            row = (int) (Math.random() * Board.getNUM_ROWS());
            col = (int) (Math.random() * Board.getNUM_COLS());

            for (Node n : snake.listNodes) {
                if (n.col == col && n.row == row) {
                    hit = true;
                }
            }
        }
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        Util.drawSquare(g, row, col, Color.YELLOW, squareWidth, squareHeight);
    }
}
