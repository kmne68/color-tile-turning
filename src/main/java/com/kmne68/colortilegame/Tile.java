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
public class Tile {
  private Color color;
  private boolean isLocked;
  private boolean isSelected;
  private int red;
  private int green;
  private int blue;
  
  public Tile(Color color) {
    this.color = color;
    this.isSelected = false;
    this.isLocked = false;
  }
  
  public void changeColor(Color newColor) {
    if (!isLocked) {
      color = newColor;
    }
  }
  
  
  public Color getColor() {
    
    return color;
  }
  
  
  public boolean isLocked() {
    return isLocked;
  }
  
  
  public void setLocked(boolean lockStatus) {
    isLocked = lockStatus;
  }

  public int getRed() {
    return red;
  }

  public int getGreen() {
    return green;
  }

  public int getBlue() {
    return blue;
  }
  
  
  public void setSelected(boolean isSelected) {
    isSelected = true;
  }
  
  public void setRed(int red) {
    this.red = red;
  }
  
  
  public void setGreen(int green) {
    this.green = green;
  }
  
  
  public void setBlue(int blue) {
    this.blue = blue;
  }
}
