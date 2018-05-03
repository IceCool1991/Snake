/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author alu26600537w
 */
public class SpecialFood extends Food {

    public SpecialFood(Snake snake) {
        super(snake);
    }

    @Override
    public void draw(Graphics g, int squareWidth, int squareHeight) {
        Random rand = new Random();
        float red = (float) (rand.nextFloat());
        float gr = (float) (rand.nextFloat());
        float bl = (float) (rand.nextFloat());
        Util.drawSquare(g, row, col, new Color(red, gr, bl), squareWidth, squareHeight);
    }
}
