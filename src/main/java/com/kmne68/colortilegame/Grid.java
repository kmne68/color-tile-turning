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
  private int tileRed, tileGreen, tileBlue;
  
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
  
  
  public int getNumCols() {
    return numCols;
  }
  
  public int getNumRows() {
    return numRows;
  }
  
  
  public Tile getTile(int row, int col) {
    return tiles[row][col];
  }
  
  
  public Color getTileColor(int row, int col) {
    return tiles[row][col].getColor();
  }
  
  
  public void setTileColor(int row, int col, Color newColor) {
    tiles[row][col].changeColor(newColor);
  }
  
  
  public boolean isTileLocked(int row, int col) {
    return tiles[row][col].isLocked();
  }

  public int getRed() {
    return tileRed;
  }

  public void setRed(int red) {
    this.tileRed = red;
  }

  public int getGreen() {
    return tileGreen;
  }

  public void setGreen(int green) {
    this.tileGreen = green;
  }

  public int getBlue() {
    return tileBlue;
  }

  public void setBlue(int blue) {
    this.tileBlue = blue;
  }
  
}
