/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortileturning.game;

/**
 *
 * @author kmne6
 */
public class Player {
  
  private int points;
  private int capturedTiles;
  private final int[] targetRGB;
  private final boolean isPlayer1;
  
  public Player(boolean isPlayer1) {
    this.isPlayer1 = isPlayer1;
    this.targetRGB = isPlayer1 ? new int[]{0, 0, 0} : new int[]{255, 255, 255};
    this.capturedTiles = 0;
  }
  
  public void startTurn(Board board) {
    points = 500 + capturedTiles + board.calculateContiguousBonus(this);
  }
  
  public void spendPoints(Tile tile, int redDelta, int greenDelta, int blueDelta) {
    int totalCost = redDelta + greenDelta + blueDelta;
    if(totalCost <= points && totalCost >= 0) {
      tile.adjustRGB(redDelta, greenDelta, blueDelta, isPlayer1);
      points -= totalCost;
      if(tile.isLocked())
        capturedTiles++;
    }
  }
  
  public int getPoints() { return points; }
  public int getCapturedTiles() { return capturedTiles; }
  public int[] getTargetRGB() { return targetRGB; }
}


