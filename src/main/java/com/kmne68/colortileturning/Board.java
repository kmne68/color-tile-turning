/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortileturning;

import java.util.Random;

/**
 *
 * @author kmne6
 */
public class Board {
  
  private Tile[][] grid;
  private int rows, cols;
  
  public Board(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    grid = new Tile[rows][cols];
    initializeBoard();
  }
  
  private void initializeBoard() {
    Random rand = new Random();
    
    // Initialize tiles with random RGB values (0-255)
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        grid[i][j] = new Tile(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
      }
    }    
  }
  
  
  public int calculateContiguousBonus(Player player) {
    // TODO: Implement flood-fill or adjacency check
    return 0; // MVP stub
  }

}
