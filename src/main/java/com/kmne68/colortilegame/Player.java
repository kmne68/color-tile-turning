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
public class Player {
  
  private int FINAL = 256;
  
  private String name;
  private int score;
  private Color targetColor;
  private int remainingPoints;
  private int playerInputRed, playerInputGreen, playerInputBlue;

  public Player(String name, Color targetColor) {
    this.name = name;
    this.score = 0;
    this.targetColor = targetColor;
    this.remainingPoints = 255;
  }
  
  
  public void addPoints(int points) {
    remainingPoints += points;
  }
  
  public void incrementScore() {
    score++;
    System.out.println("SCORE: " + score);
  }
  
  
  public String getName() {
    return name;
  }
  
  
  public int getRemainingPoints() {
    return remainingPoints;
  }
  
  
  public int getScore() {
    return score;
  }
  
  
  public Color getTargetColor() {
    return targetColor;
  }
  
  public void subtractPoints(int points) {
    remainingPoints -= points;
  }

  public int getPlayerInputRed() {
    return playerInputRed;
  }

  public void setPlayerInputRed(int playerInputRed) {
    this.playerInputRed = playerInputRed;
    System.out.println("PLAYER.setPlayerInputRed: " + playerInputRed);
  }

  public int getPlayerInputGreen() {
    return playerInputGreen;
  }

  public void setPlayerInputGreen(int playerInputGreen) {
    this.playerInputGreen = playerInputGreen;
    System.out.println("PLAYER.setPlayerInputGreen: " + playerInputGreen);
  }

  public int getPlayerInputBlue() {
    return playerInputBlue;
  }

  public void setPlayerInputBlue(int playerInputBlue) {
    this.playerInputBlue = playerInputBlue;
    System.out.println("PLAYER.setPlayerInputBlue: " + playerInputBlue);
  }
  
  
  
}
