/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortileturning;

/**
 *
 * @author kmne6
 */
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
