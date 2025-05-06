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
  
  
  public Tile getTile(int row, int col) {
    return (row >= 0 && row < rows && col >= 0 && col < cols) ? grid[row][col] : null;
  }
  
  
  public int calculateContiguousBonus(Player player) {
    
    int bonus = 0;
    boolean[][] visited = new boolean[rows][cols];
    int[] targetRGB = player.getTargetRGB();
    
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (!visited[i][j] && isPlayerTile(i, j, targetRGB)) {
          System.out.println("CONTIGUOUS: " + countContiguous(i, j, targetRGB, visited));
          bonus += countContiguous(i, j, targetRGB, visited);
        }
      }
    }
    return bonus;
  }
  
  
  private boolean isPlayerTile(int row, int col, int[] targetRGB) {
    Tile tile = getTile(row, col);
    if(tile == null || !tile.isLocked()) return false;
    int[] rgb = tile.getRGB();
    return rgb[0] == targetRGB[0] && rgb[1] == targetRGB[1] && rgb[2] == targetRGB[2];
  }
  
  
  private int countContiguous(int row, int col, int[] targetRGB, boolean[][] visited) {
    if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] || !isPlayerTile(row, col, targetRGB)) {
          return 0;
    }
    visited[row][col] = true;
    int count = 1;
    count += countContiguous(row - 1, col, targetRGB, visited); // up
    count += countContiguous(row + 1, col, targetRGB, visited); // down
    count += countContiguous(row, col - 1, targetRGB, visited); // left
    count += countContiguous(row, col + 1, targetRGB, visited); // right
    return count;
  }

}
