package com.kmne68.colortileturning;

/**
 * Represents a player with points, captured tiles, and target color.
 */
public class Player {
    private int points;
    private int capturedTiles;
    private final int[] targetRGB;
    private boolean isPlayer1;
    private int score;


    public Player(boolean isPlayer1) {
        this.isPlayer1 = isPlayer1;
        this.targetRGB = isPlayer1 ? new int[]{0, 0, 0} : new int[]{255, 255, 255};
        this.capturedTiles = 0;
        this.points = 0;
    }

    public void startTurn(Board board) {
        int contiguousBonus = board.calculateContiguousBonus(this);
        points = 255 + capturedTiles + contiguousBonus;  // Base per GDD discussion
        System.out.println("Points: " + points);
    }

    public void spendPoints(Tile tile, int rDelta, int gDelta, int bDelta) {
        int totalCost = rDelta + gDelta + bDelta;
        if (totalCost <= points && totalCost >= 0) {
            tile.adjustRGB(rDelta, gDelta, bDelta, isPlayer1);
            points -= totalCost;
            if (tile.isLocked()) {
                capturedTiles++;
            }
        }
    }


    public int getCapturedTiles() { return capturedTiles; }
    public int[] getTargetRGB() { return targetRGB; }

    public String getName() { return "Player"; } // Or your field
    public boolean isPlayer1() { 
    return isPlayer1;  // Return the field (boolean you added earlier)
}
    public int getPointsAvailable() { return points; } // Your field
    public void spendPoints(int amount) { points -= amount; if (points < 0) points = 0; }
    public void addPoints(int amount) { points += amount; }

    public int getScore() {
      return score;
    }

    public void setAsPlayer1(boolean isP1) {
      this.isPlayer1 = isP1;
    }
}



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// package com.kmne68.colortileturning;

/**
 *
 * @author kmne6
 */

/*
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
  public boolean getIsPlayer1() { return isPlayer1; }
}

*/