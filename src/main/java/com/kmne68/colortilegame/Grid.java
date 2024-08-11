/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import java.awt.Color;

/**
 *
 * @author kmne6
 */
public class Grid {
  
  private Tile[][] tiles;
  private int numRows;
  private int numCols;
  
  public Grid(int numRows, int numCols) {
    this.numRows = numRows;
    this.numCols = numCols;
    tiles = new Tile[numRows][numCols];
    
    // Intialize tiles with random colors
    for(int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        tiles[row][col] = new Tile(new Color((int) (Math.random() * 256),
          (int) (Math.random() * 256), (int) (Math.random() * 256)));
      }
    }
  }
  
  public Tile getTile(int row, int col) {
    return tiles[row][col];
  }
  
  
  
}
