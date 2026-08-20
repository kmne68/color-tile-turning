package com.kmne68.colortileturning;

/**
 * Represents a single tile on the game board.
 */
public class Tile {
    private int r, g, b;
    private boolean locked;
    private final int row;
    private final int col;


    public Tile(int r, int g, int b, int row, int col) {
        this.r = Math.max(0, Math.min(255, r));
        this.g = Math.max(0, Math.min(255, g));
        this.b = Math.max(0, Math.min(255, b));
        this.locked = false;
        this.row = row;
        this.col = col;
    }

    public void adjustRGB(int rDelta, int gDelta, int bDelta, boolean isPlayer1) {
        if (!locked) {
            r = Math.max(0, Math.min(255, r + (isPlayer1 ? -rDelta : rDelta)));
            g = Math.max(0, Math.min(255, g + (isPlayer1 ? -gDelta : gDelta)));
            b = Math.max(0, Math.min(255, b + (isPlayer1 ? -bDelta : bDelta)));

            if ((isPlayer1 && r == 0 && g == 0 && b == 0) ||
                (!isPlayer1 && r == 255 && g == 255 && b == 255)) {
                locked = true;
            }
        }
    }

    public int[] getRGB() { return new int[]{r, g, b}; }
    public boolean isLocked() { return locked; }
    public int getRow() { return row; }
    public int getCol() { return col; }

    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }
    public void setRGB(int r, int g, int b) { this.r = r; this.g = g; this.b = b; }
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
public class Tile {
  private int red, green, blue;
  private boolean locked;
  
  public Tile(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.locked = false;
  }
  
  public void adjustRGB(int redDelta, int greenDelta, int blueDelta, boolean isPlayer1) {
    if(!locked) {
      red   = Math.max(0, Math.min(255, red   + (isPlayer1 ? -redDelta   : redDelta)));
      green = Math.max(0, Math.min(255, green + (isPlayer1 ? -greenDelta : greenDelta)));
      blue  = Math.max(0, Math.min(255, blue  + (isPlayer1 ? -blueDelta  : blueDelta)));
      if ((isPlayer1 && red == 0 && green == 0 && blue == 0) || (!isPlayer1 && red == 255 && green == 255 && blue == 255)) {
        locked = true;
      }
    }
  }
  
  public int[] getRGB() { return new int[] { red, green, blue }; }
  public boolean isLocked() { return locked; }
}
*/