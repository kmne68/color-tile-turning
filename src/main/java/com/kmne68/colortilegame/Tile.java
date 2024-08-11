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
  
  public Tile(Color color) {
    this.color = color;
    this.isLocked = false;
  }
  
  public void changeColor(Color newColor) {
    if (!isLocked) {
      color = newColor;
    }
  }
}
