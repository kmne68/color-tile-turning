package com.kmne68.colortileturning;

/**
 * Represents a single tile on the game board.
 */
public class Tile {
    private int r, g, b;
    private boolean locked;
    private final int row;
    private final int col;
    private String tileOwner;


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
          System.out.println("LOCKED top: " + this.isLocked());
            r = Math.max(0, Math.min(255, r + (isPlayer1 ? -rDelta : rDelta)));
            g = Math.max(0, Math.min(255, g + (isPlayer1 ? -gDelta : gDelta)));
            b = Math.max(0, Math.min(255, b + (isPlayer1 ? -bDelta : bDelta)));
/*
            if ((isPlayer1 && r == 0 && g == 0 && b == 0) ||
                (!isPlayer1 && r == 255 && g == 255 && b == 255)) {
                locked = true;
                System.out.println("LOCKED bottom: " + this.isLocked());
                this.setTileOwner(isPlayer1 ? "P1" : "P2");
            }
            */
        }
    }

    public int[] getRGB() { return new int[]{r, g, b}; }

    public boolean isLocked() { return locked; }
    
    public String getTileOwner() { return tileOwner; }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }
    public void setRGB(int r, int g, int b) { 
      this.r = r; this.g = g; this.b = b;
      System.out.println("Tile.setRGB ==> RGB VALUES SET");  
    }

    public void setTileOwner(String tileOwner) {
      this.tileOwner = tileOwner;
    }

    public void setLocked(boolean isLocked) {
      this.locked = isLocked;
    }
}
