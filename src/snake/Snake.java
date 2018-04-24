/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author alu26600537w
 */
public class Snake {

    private ArrayList<Node> listNodes;
    private DirectionType direction;
    public int snakeLength;

    
    public Snake(int snakeLength) {
        listNodes = new ArrayList<Node>();
        for(int i = 0; i<3;i++){
            listNodes.add(new Node(Board.NUM_ROWS/2, Board.NUM_COLS/2 - i, Color.BLUE));
        }
        this.direction = DirectionType.RIGHT;
        this.snakeLength = snakeLength;
    }
    
    public void eatFood(Node node){
        listNodes.add(node);
        snakeLength++;
    }
    
    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node node: listNodes) {
            Util.drawSquare(g, node.row, node.col, node.color, squareWidth, squareHeight);
        }
    }
    
//    private void move() {
//
//        for (int z = snake.snakeLength; z > 0; z--) {
//            x[z] = x[(z - 1)];
//            y[z] = y[(z - 1)];
//        }
//
//        switch (direction) {
//            case LEFT:
//                x[0] -= DOT_SIZE;
//                break;
//            case RIGHT:
//                x[0] += DOT_SIZE;
//                break;
//            case UP:
//                y[0] -= DOT_SIZE;
//                break;
//            case DOWN:
//                y[0] += DOT_SIZE;
//                break;
//            default:
//                break;
//        }
//    }
}