/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;

/**
 *
 * @author alu26600537w
 */
public class Node {

    public int row;
    public int col;
    public Color color;

    public Node(int row, int col, Color color) {
        this.col = col;
        this.row = row;
        this.color = color;
    }
}