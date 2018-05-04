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

    protected ArrayList<Node> listNodes;
    private DirectionType direction;
    public boolean isTurning;

    public Snake(int snakeLength) {
        isTurning = false;
        listNodes = new ArrayList<Node>();
        for (int i = 0; i < snakeLength; i++) {
            listNodes.add(new Node(Board.getNUM_ROWS() / 2, Board.getNUM_COLS() / 2 - i, Color.GREEN));
        }
        Node head = listNodes.get(0);
        head.color = Color.RED;
        direction = DirectionType.RIGHT;
    }

    public void eatFood() {
        Node head = listNodes.get(0);
        Node node = new Node(head.row, head.col, head.color);
        switch (direction) {
            case LEFT:
                node.col--;
                listNodes.add(0, node);
                break;
            case RIGHT:
                node.col++;
                listNodes.add(0, node);
                break;
            case UP:
                node.row--;
                listNodes.add(0, node);
                break;
            case DOWN:
                node.row++;
                listNodes.add(0, node);
                break;
            default:
                break;
        }
        head.color = Color.GREEN;
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node node : listNodes) {
            Util.drawSquare(g, node.row, node.col, node.color, squareWidth, squareHeight);
        }
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }

    public void move() {
        Node head = listNodes.get(0);
        Node node = new Node(head.row, head.col, head.color);
        switch (direction) {
            case LEFT:
                node.col--;
                listNodes.add(0, node);
                break;
            case RIGHT:
                node.col++;
                listNodes.add(0, node);
                break;
            case UP:
                node.row--;
                listNodes.add(0, node);
                break;
            case DOWN:
                node.row++;
                listNodes.add(0, node);
                break;
            default:
                break;
        }
        head.color = Color.GREEN;
        listNodes.remove(listNodes.size() - 1);
    }
}
