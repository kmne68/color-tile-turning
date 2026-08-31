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

    public String spendPoints(Tile tile, int rDelta, int gDelta, int bDelta) {
      System.out.println("in spendPoints");
        int totalCost = rDelta + gDelta + bDelta;
        if (totalCost <= points && totalCost >= 0) {
            tile.adjustRGB(rDelta, gDelta, bDelta, isPlayer1);
            points -= totalCost;
            System.out.println("spendPoints TILE IS LOCKED: " + tile.isLocked());
            if (tile.isLocked()) {
                capturedTiles++;
            }
        }
        return "STRAIGHT OUT OF spendPoints()";
    }


    public int getCapturedTiles() { return capturedTiles; }


    public int[] getTargetRGB() { return targetRGB; }

    public String getName() { 
      if (isPlayer1)
        return "Player 1 + test";
      else {
        return "Player 2";
      }
    } // Or your field



    public boolean isPlayer1() { 
    return isPlayer1;  // Return the field (boolean you added earlier)
}
    public int getPointsAvailable() { return points; } // Your field
//    public void spendPoints(int amount) { points -= amount; if (points < 0) points = 0; }
    public void addPoints(int amount) { points += amount; }

    public int getScore() {
      return score;
    }

    public void setAsPlayer1(boolean isP1) {
      this.isPlayer1 = isP1;
    }
}
