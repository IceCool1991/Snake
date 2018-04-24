/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

/**
 *
 * @author alu26600537w
 */
public class Food {

    private Node node;

    public Food(Snake snake) {

        // Set our food's x & y position to a random position
        int row = (int) (Math.random() * Board.NUM_ROWS);
        int col = (int) (Math.random() * Board.NUM_COLS);
        
        
    }
}
